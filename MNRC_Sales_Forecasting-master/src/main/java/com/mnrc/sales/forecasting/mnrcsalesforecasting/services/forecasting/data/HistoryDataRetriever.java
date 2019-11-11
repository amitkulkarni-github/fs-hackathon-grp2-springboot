package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.data;

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
 * The type History data retriever.
 */
@Component
public class HistoryDataRetriever {

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
    public void getHistoryForecastDetails(ForecastInput forecastInput) {
        UnitSalesDetails unitSalesDetails = new UnitSalesDetails();
        forecastInput.setUnitSalesDetails(unitSalesDetails);
        Long l = forecastInput.getHistoryStartDate().until(forecastInput.getForecastStartDate(), ChronoUnit.DAYS);
        forecastInput.setDaysAheadOfHistoryStartDate(l.intValue());
        Long b = forecastInput.getHistoryStartDate().until(forecastInput.getForecastEndDate(), ChronoUnit.DAYS);
        populateHistoryDetails(forecastInput, unitSalesDetails);
        forecastInput.setForecastPeriod(b.intValue());
    }

    /**
     * Populate history details.
     *
     * @param forecastInput    the forecast input
     * @param unitSalesDetails the unit sales details
     */
    private void populateHistoryDetails(ForecastInput forecastInput, UnitSalesDetails unitSalesDetails) {
        List<UnitDetails> datalist = historyService.getUnitSalesDetails(forecastInput.getHistoryStartDate(), forecastInput.getHistoryEndDate(), forecastInput.getChannelId(), forecastInput.getProductId());
        unitSalesDetails.setHistoryUnitDetails(datalist);
    }
}
