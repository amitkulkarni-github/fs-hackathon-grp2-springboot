package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ForecastingException;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.*;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper.ForecastRequestMapper;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper.ForecastResponseMapper;
import com.workday.insights.timeseries.arima.struct.ForecastResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * The type Forecasting service.
 */
@Service("ForecastingService")
public class ForecastingService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * The Forecast request mapper.
     */
    @Autowired
    ForecastRequestMapper forecastRequestMapper;

    /**
     * The Forecast response mapper.
     */
    @Autowired
    ForecastResponseMapper forecastResponseMapper;

    @Autowired
    ArimaInvoker arimaInvoker;

    /**
     * Gets forecast data async.
     *
     * @param forecastInput     the forecast input
     * @param forecastResponse  the forecast response
     * @param forecastMethod    the forecast method
     * @param isSeasonal        the is seasonal
     * @param seasonalFrequency the seasonal frequency
     * @return the forecast data async
     * @throws Exception the exception
     */
//Async
    public CompletableFuture<Boolean> getForecastDataAsync(ForecastInput forecastInput,
                                                           ForecastResponse forecastResponse,String forecastMethod, String forecastMethodName, boolean isSeasonal,
                                                           int seasonalFrequency) {
        ArimaRequest arimaRequest = forecastRequestMapper.getArimaParams(forecastMethod,
                isSeasonal,seasonalFrequency,forecastInput.getUnitSalesDetails());
        LOG.info("The forecast method Id: {} Method Name: {}",forecastMethod, forecastMethodName);
        Forecast forecast = new Forecast();
        forecast.setMethodId(forecastMethod);
        List<UnitDetails> unitDetailsList = null;
        try {
            unitDetailsList = forecastResponseMapper.getArimaResponse(processArimaForecast(forecastInput, arimaRequest), forecastInput);
        }catch (ForecastingException e){
            unitDetailsList = new ArrayList<>();
        }
        forecast.setData(unitDetailsList);
        forecastResponse.getForecast().add(forecast);
        return CompletableFuture.completedFuture(true);
    }


    /**
     * Process arima forecast arima response.
     *
     * @param forecastInput the forecast input
     * @param arimaRequest  the arima request
     * @return the arima response
     */
    public ArimaResponse processArimaForecast(ForecastInput forecastInput, ArimaRequest arimaRequest){
        ForecastResult forecastResult = arimaInvoker.getForecastResult(arimaRequest,forecastInput);
        ArimaResponse arimaResponse = new ArimaResponse();
        if(Optional.of(forecastResult).isPresent()){
            arimaResponse.setForecastArray(forecastResult.getForecast());
            arimaResponse.setUppers(forecastResult.getForecastUpperConf());
            arimaResponse.setLowers(forecastResult.getForecastLowerConf());
            arimaResponse.setRmse(forecastResult.getRMSE());
            arimaResponse.setMaxNormalizedVariance(forecastResult.getMaxNormalizedVariance());
        }
        return arimaResponse;
    }

    /**
     * Gets forecast data.
     *
     * @param forecastInput the forecast input
     * @return the forecast data
     * @throws Exception the exception
     */
//Non Async
    public List<UnitDetails> getForecastData(ForecastInput forecastInput) {
        ArimaRequest arimaRequest = forecastRequestMapper.getArimaParams(forecastInput.getMethod(),
                forecastInput.isSeasonal(),forecastInput.getSeasonalFrequency(),forecastInput.getUnitSalesDetails());
        ArimaResponse arimaResponse = processArimaForecast(forecastInput,arimaRequest);
        return forecastResponseMapper.getArimaResponse(arimaResponse, forecastInput);
    }
}
