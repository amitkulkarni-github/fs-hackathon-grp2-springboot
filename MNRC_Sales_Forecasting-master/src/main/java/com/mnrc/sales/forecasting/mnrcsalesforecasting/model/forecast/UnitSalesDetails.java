package com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast;

import java.util.List;

/**
 * The type Unit sales details.
 */
public class UnitSalesDetails {
    private List<UnitDetails> historyUnitDetails;
    private List<UnitDetails> forecastUnitDetails;

    /**
     * Gets history unit details.
     *
     * @return the history unit details
     */
    public List<UnitDetails> getHistoryUnitDetails() {
        return historyUnitDetails;
    }

    /**
     * Sets history unit details.
     *
     * @param historyUnitDetails the history unit details
     */
    public void setHistoryUnitDetails(List<UnitDetails> historyUnitDetails) {
        this.historyUnitDetails = historyUnitDetails;
    }

    /**
     * Gets forecast unit details.
     *
     * @return the forecast unit details
     */
    public List<UnitDetails> getForecastUnitDetails() {
        return forecastUnitDetails;
    }

    /**
     * Sets forecast unit details.
     *
     * @param forecastUnitDetails the forecast unit details
     */
    public void setForecastUnitDetails(List<UnitDetails> forecastUnitDetails) {
        this.forecastUnitDetails = forecastUnitDetails;
    }

    @Override
    public String toString() {
        return "ProductSalesDetails{" +
                "historUnitDetails=" + historyUnitDetails +
                ", forecastUnitDetails=" + forecastUnitDetails +
                '}';
    }
}
