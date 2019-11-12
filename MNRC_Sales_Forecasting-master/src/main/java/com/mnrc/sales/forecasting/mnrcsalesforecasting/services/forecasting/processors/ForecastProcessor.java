package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.processors;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.Forecast;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastResponse;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.ForecastingService;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.data.HistoryDataRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    HistoryDataRetriever historyDataRetriever;

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
    public ForecastResponse processInput(ForecastInput forecastInput){
        historyDataRetriever.getHistoryForecastDetails(forecastInput);
        ForecastResponse forecastResponse = new ForecastResponse();
        if(null != forecastInput.getMethod()){
            Forecast forecast = new Forecast();
            forecast.setMethodName(forecastInput.getMethod());
            forecast.setData(forecastingService.getForecastData(forecastInput));
            List<Forecast> forecasts = new ArrayList<>();
            forecasts.add(forecast);
            forecastResponse.setForecast(forecasts);
        }
        else{
            CompletableFuture<Boolean> firstOrder= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dc75e0ac0bd39e99bf3d69f",false, 0);
            CompletableFuture<Boolean> differencedFirstOrder= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dc75e0ac0bd39e99bf3d69b",false, 0);
            CompletableFuture<Boolean> ses= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dc75e0ac0bd39e99bf3d699",false, 0);
            CompletableFuture<Boolean> dampedTrendLinearExponentialSmoothing= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dc75e0ac0bd39e99bf3d69d",false, 0);

            CompletableFuture<Boolean> firstOrderSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dca45b5ba26446724ed00ed", true, 12);
            CompletableFuture<Boolean> differencedFirstOrderSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dca4586ba26446724ed00ec",true, 12);
            CompletableFuture<Boolean> sesSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dca4554ba26446724ed00eb",true, 12);
            CompletableFuture<Boolean> dampedSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dca45cfba26446724ed00ee",true, 12);
            CompletableFuture.allOf(firstOrder,differencedFirstOrder,ses,dampedTrendLinearExponentialSmoothing,firstOrderSeasonal,differencedFirstOrderSeasonal,sesSeasonal,dampedSeasonal).join();
        }
        forecastResponse.setHistoryDetails(forecastInput.getUnitSalesDetails().getHistoryUnitDetails());
        return forecastResponse;
    }
}
