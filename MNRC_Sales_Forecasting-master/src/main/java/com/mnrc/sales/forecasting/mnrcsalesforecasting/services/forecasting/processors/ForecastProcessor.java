package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.processors;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastResponse;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.ForecastingService;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mock.ForecastDataCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * The type Forecast processor.
 */
@Service
public class ForecastProcessor {

    /**
     * The Forecast data creator.
     */
    @Autowired
    ForecastDataCreator forecastDataCreator;

    /**
     * The Forecasting service.
     */
    @Autowired
    ForecastingService forecastingService;

    /**
     * Process input forecast response.
     *
     * @param forecastInput the forecast input
     * @return the forecast response
     * @throws Exception the exception
     */
    public ForecastResponse processInput(ForecastInput forecastInput) throws Exception {
        forecastDataCreator.getHistoryForecastDetails(forecastInput);
        ForecastResponse forecastResponse = new ForecastResponse();
        if(null != forecastInput.getMethod()){
            forecastResponse.getForecastMap().put(forecastInput.getMethod(),forecastingService.getForecastData(forecastInput));
        }
        else{
            CompletableFuture<Boolean> firstOrder= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"first-order",false, 0);
            CompletableFuture<Boolean> differencedFirstOrder= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"differenced-first-order",false, 0);
            CompletableFuture<Boolean> ses= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"SES",false, 0);
            CompletableFuture<Boolean> dampedTrendLinearExponentialSmoothing= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"damped-trend-linear-exponential-smoothing",false, 0);

            CompletableFuture<Boolean> firstOrderSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"first-order", true, 12);
            CompletableFuture<Boolean> differencedFirstOrderSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"differenced-first-order",true, 12);
            CompletableFuture<Boolean> sesSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"SES",true, 12);
            CompletableFuture<Boolean> dampedSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"damped-trend-linear-exponential-smoothing",true, 12);
            CompletableFuture.allOf(firstOrder,differencedFirstOrder,ses,dampedTrendLinearExponentialSmoothing,firstOrderSeasonal,differencedFirstOrderSeasonal,sesSeasonal,dampedSeasonal).join();
        }
        forecastResponse.setHistoryDetails(forecastInput.getUnitSalesDetails().getHistoryUnitDetails());
        return forecastResponse;
    }
}
