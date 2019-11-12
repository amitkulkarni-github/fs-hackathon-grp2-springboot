package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.processors;
import static com.mnrc.sales.forecasting.mnrcsalesforecasting.util.ApplicationConstants.*;
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
            forecast.setMethodId(forecastInput.getMethod());
            forecast.setData(forecastingService.getForecastData(forecastInput));
            List<Forecast> forecasts = new ArrayList<>();
            forecasts.add(forecast);
            forecastResponse.setForecast(forecasts);
        }
        else{
            CompletableFuture<Boolean> firstOrder= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,
                    FIRST_ORDER_ID, FIRST_ORDER_NAME, false, 0);

            CompletableFuture<Boolean> differencedFirstOrder= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,
                    DIFFERENCED_FIRST_ORDER_ID, DIFFERENCED_FIRST_ORDER_NAME,false, 0);

            CompletableFuture<Boolean> ses= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,
                    SES_ID,SES_NAME,false, 0);

            CompletableFuture<Boolean> dampedTrendLinearExponentialSmoothing= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,
                    DTLES_ID,DTLES_NAME,false, 0);

            CompletableFuture<Boolean> firstOrderSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,
                    FIRST_ORDER_SEASONAL_ID, FIRST_ORDER_SEASONAL_NAME,true, 12);

            CompletableFuture<Boolean> differencedFirstOrderSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,
                    DIFFERENCED_FIRST_ORDER_SEASONAL_ID, DIFFERENCED_FIRST_ORDER_SEASONAL_NAME, true, 12);

            CompletableFuture<Boolean> sesSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,
                    SES_SEASONAL_ID, SES_SEASONAL_NAME, true, 12);

            CompletableFuture<Boolean> dampedSeasonal= forecastingService.getForecastDataAsync(forecastInput,forecastResponse,
                    DTLES_SEASONAL_ID,DTLES_SEASONAL_NAME,true, 12);

            CompletableFuture.allOf(firstOrder,differencedFirstOrder,ses,dampedTrendLinearExponentialSmoothing,firstOrderSeasonal,differencedFirstOrderSeasonal,sesSeasonal,dampedSeasonal).join();
        }
        forecastResponse.setHistoryDetails(forecastInput.getUnitSalesDetails().getHistoryUnitDetails());
        return forecastResponse;
    }
}
