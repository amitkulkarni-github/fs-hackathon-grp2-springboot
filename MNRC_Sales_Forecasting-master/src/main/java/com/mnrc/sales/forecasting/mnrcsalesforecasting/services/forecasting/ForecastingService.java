package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.*;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper.ForecastRequestMapper;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper.ForecastResponseMapper;
import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ForecastResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The type Forecasting service.
 */
@Service("ForecastingService")
public class ForecastingService {

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
    public CompletableFuture<Boolean> getForecastDataAsync(ForecastInput forecastInput, ForecastResponse forecastResponse,String forecastMethod, boolean isSeasonal, int seasonalFrequency) throws Exception {
        ArimaRequest arimaRequest = forecastRequestMapper.getArimaParams(forecastMethod,
                isSeasonal,seasonalFrequency,forecastInput.getUnitSalesDetails());
        if(isSeasonal) {
            forecastResponse.getForecastMap().put(forecastMethod, forecastResponseMapper.getArimaResponse(processArimaForecast(forecastInput, arimaRequest), forecastInput));
        } else{
            forecastResponse.getForecastMap().put(forecastMethod+"-seasonal", forecastResponseMapper.getArimaResponse(processArimaForecast(forecastInput, arimaRequest), forecastInput));
        }
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
        ForecastResult forecastResult = Arima.forecast_arima(arimaRequest.getHistoryArray(),
                forecastInput.getForecastPeriod(), arimaRequest.getArimaParams());
        ArimaResponse arimaResponse = new ArimaResponse();
        arimaResponse.setForecastArray(forecastResult.getForecast());
        arimaResponse.setUppers(forecastResult.getForecastUpperConf());
        arimaResponse.setLowers(forecastResult.getForecastLowerConf());
        arimaResponse.setRmse(forecastResult.getRMSE());
        arimaResponse.setMaxNormalizedVariance(forecastResult.getMaxNormalizedVariance());
        System.out.println("Arima Response: "+ arimaResponse.toString());
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
    public List<UnitDetails> getForecastData(ForecastInput forecastInput) throws Exception {
        ArimaRequest arimaRequest = forecastRequestMapper.getArimaParams(forecastInput.getMethod(),
                forecastInput.isSeasonal(),forecastInput.getSeasonalFrequency(),forecastInput.getUnitSalesDetails());
        ArimaResponse arimaResponse = processArimaForecast(forecastInput,arimaRequest);
        return forecastResponseMapper.getArimaResponse(arimaResponse, forecastInput);
    }
}
