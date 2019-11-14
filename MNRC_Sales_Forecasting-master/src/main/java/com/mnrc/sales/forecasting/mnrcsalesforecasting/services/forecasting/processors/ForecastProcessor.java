package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.processors;

import static com.mnrc.sales.forecasting.mnrcsalesforecasting.util.ApplicationConstants.*;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.Forecast;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastResponse;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.ForecastingService;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.data.HistoryDataRetriever;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * The type Forecast processor.
 */
@Service

public class ForecastProcessor {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

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
    public ForecastResponse processInput(ForecastInput forecastInput) {
        historyDataRetriever.getHistoryForecastDetails(forecastInput);
        ForecastResponse forecastResponse = new ForecastResponse();
        if (null != forecastInput.getMethod()) {
            ForecastResponse seasonalResponse = new ForecastResponse();
            callForecastProcess(forecastInput, seasonalResponse);
            if (!CollectionUtils.isEmpty(seasonalResponse.getForecast())) {
                Optional<Forecast> seasonalForecast = seasonalResponse.getForecast().stream().filter(Objects::nonNull)
                        .filter(e -> StringUtils.equals(forecastInput.getMethod(), e.getMethodId())).findAny();
                Forecast forecast = new Forecast();
                forecast.setMethodId(forecastInput.getMethod());
                if (seasonalForecast.isPresent()) {
                    forecast.setData(seasonalForecast.get().getData());
                } else {
                    forecast.setData(new ArrayList<>());
                }
                List<Forecast> forecasts = new ArrayList<>();
                forecasts.add(forecast);
                forecastResponse.setForecast(forecasts);
            }
        } else {
            callForecastProcess(forecastInput, forecastResponse);
        }
        forecastResponse.setHistoryDetails(forecastInput.getUnitSalesDetails().getHistoryUnitDetails());
        return forecastResponse;
    }

    public void callForecastProcess(ForecastInput forecastInput, ForecastResponse forecastResponse) {
        List<CompletableFuture<Boolean>> futureForecastList = new ArrayList<>();

        futureForecastList.add(forecastingService.getForecastDataAsync(forecastInput, forecastResponse,
                FIRST_ORDER_ID, FIRST_ORDER_NAME, false, 0));

        futureForecastList.add(forecastingService.getForecastDataAsync(forecastInput, forecastResponse,
                DIFFERENCED_FIRST_ORDER_ID, DIFFERENCED_FIRST_ORDER_NAME, false, 0));

        futureForecastList.add(forecastingService.getForecastDataAsync(forecastInput, forecastResponse,
                SES_ID, SES_NAME, false, 0));

        futureForecastList.add(forecastingService.getForecastDataAsync(forecastInput, forecastResponse,
                DTLES_ID, DTLES_NAME, false, 0));

        futureForecastList.add(forecastingService.getForecastDataAsync(forecastInput, forecastResponse,
                FIRST_ORDER_SEASONAL_ID, FIRST_ORDER_SEASONAL_NAME, true, 12));

        futureForecastList.add(forecastingService.getForecastDataAsync(forecastInput, forecastResponse,
                DIFFERENCED_FIRST_ORDER_SEASONAL_ID, DIFFERENCED_FIRST_ORDER_SEASONAL_NAME, true, 12));

        futureForecastList.add(forecastingService.getForecastDataAsync(forecastInput, forecastResponse,
                SES_SEASONAL_ID, SES_SEASONAL_NAME, true, 12));

        futureForecastList.add(forecastingService.getForecastDataAsync(forecastInput, forecastResponse,
                DTLES_SEASONAL_ID, DTLES_SEASONAL_NAME, true, 12));

        List<CompletableFuture<Boolean>> completableFutureList =
                futureForecastList.stream().filter(booleanCompletableFuture ->
                        !booleanCompletableFuture.isCompletedExceptionally()).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(completableFutureList)) {
            CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()])).join();
        }
    }
}
