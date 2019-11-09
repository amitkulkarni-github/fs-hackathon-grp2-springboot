package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mock;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitSalesDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.history.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Forecast data creator.
 */
@Component
public class ForecastDataCreator {

    /**
     * The Enable mock forecasting.
     */
    @Value("${enableMockForecasting:false}")
    boolean enableMockForecasting;

    /**
     * The History service.
     */
    @Autowired
    protected HistoryService historyService;

    /**
     * Gets history forecast details.
     *
     * @param forecastInput the forecast input
     */
// only for testing
    // in real scenario the product sales needs to come from db
    public void getHistoryForecastDetails(ForecastInput forecastInput) {
        UnitSalesDetails unitSalesDetails = new UnitSalesDetails();
        forecastInput.setUnitSalesDetails(unitSalesDetails);
        Long l = forecastInput.getHistoryStartDate().until(forecastInput.getForecastStartDate(), ChronoUnit.DAYS);
        forecastInput.setDaysAheadOfHistoryStartDate(l.intValue());
        Long b = forecastInput.getHistoryStartDate().until(forecastInput.getForecastEndDate(), ChronoUnit.DAYS);
        populateHistoryDetails(enableMockForecasting, forecastInput, unitSalesDetails);
        forecastInput.setForecastPeriod(b.intValue());
    }

    /**
     * Populate history details.
     *
     * @param enableMockForecasting the enable mock forecasting
     * @param forecastInput         the forecast input
     * @param unitSalesDetails      the unit sales details
     */
    public void populateHistoryDetails(boolean enableMockForecasting, ForecastInput forecastInput, UnitSalesDetails unitSalesDetails) {
        if (enableMockForecasting) {
            List<UnitDetails> datalist = new ArrayList<>();
            Random r = new Random();
            double rangeMin = 20;
            double rangeMax = 10000;
            LocalDate start = forecastInput.getHistoryStartDate();
            LocalDate stop = forecastInput.getHistoryEndDate();
            LocalDate historyStartDate = start;
            while (historyStartDate.isBefore(stop)) {

                datalist.add(new UnitDetails("Online", "Phone", historyStartDate, r.nextDouble() * 100D  ));
                // Set up the next loop.
                historyStartDate = historyStartDate.plusDays(1);
            }
            unitSalesDetails.setHistoryUnitDetails(datalist);
        }else{
            List<UnitDetails> datalist = historyService.getUnitSalesDetails(forecastInput.getHistoryStartDate(),forecastInput.getHistoryEndDate());
            unitSalesDetails.setHistoryUnitDetails(datalist);
        }
    }
}
