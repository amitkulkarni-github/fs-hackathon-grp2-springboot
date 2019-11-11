package com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type Forecast input.
 */
public class ForecastInput implements Serializable {
    private String method;
    private boolean isSeasonal;
    private int seasonalFrequency;
    private String channelId;
    private String productId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate historyStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate historyEndDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate forecastStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate forecastEndDate;
    private int daysAheadOfHistoryStartDate;
    private int forecastPeriod;
    private UnitSalesDetails unitSalesDetails;

    public ForecastInput() {
    }

    public ForecastInput(String method, boolean isSeasonal, int seasonalFrequency, String channelId, String productId, LocalDate historyStartDate, LocalDate historyEndDate, LocalDate forecastStartDate, LocalDate forecastEndDate, int daysAheadOfHistoryStartDate, int forecastPeriod, UnitSalesDetails unitSalesDetails) {
        this.method = method;
        this.isSeasonal = isSeasonal;
        this.seasonalFrequency = seasonalFrequency;
        this.channelId = channelId;
        this.productId = productId;
        this.historyStartDate = historyStartDate;
        this.historyEndDate = historyEndDate;
        this.forecastStartDate = forecastStartDate;
        this.forecastEndDate = forecastEndDate;
        this.daysAheadOfHistoryStartDate = daysAheadOfHistoryStartDate;
        this.forecastPeriod = forecastPeriod;
        this.unitSalesDetails = unitSalesDetails;
    }

    /**
     * Gets forecast period.
     *
     * @return the forecast period
     */
    public int getForecastPeriod() {
        return forecastPeriod;
    }

    /**
     * Sets forecast period.
     *
     * @param forecastPeriod the forecast period
     */
    public void setForecastPeriod(int forecastPeriod) {
        this.forecastPeriod = forecastPeriod;
    }

    /**
     * Gets unit sales details.
     *
     * @return the unit sales details
     */
    public UnitSalesDetails getUnitSalesDetails() {
        return unitSalesDetails;
    }

    /**
     * Sets unit sales details.
     *
     * @param unitSalesDetails the unit sales details
     */
    public void setUnitSalesDetails(UnitSalesDetails unitSalesDetails) {
        this.unitSalesDetails = unitSalesDetails;
    }

    /**
     * Gets days ahead of history start date.
     *
     * @return the days ahead of history start date
     */
    public int getDaysAheadOfHistoryStartDate() {
        return daysAheadOfHistoryStartDate;
    }

    /**
     * Sets days ahead of history start date.
     *
     * @param daysAheadOfHistoryStartDate the days ahead of history start date
     */
    public void setDaysAheadOfHistoryStartDate(int daysAheadOfHistoryStartDate) {
        this.daysAheadOfHistoryStartDate = daysAheadOfHistoryStartDate;
    }

    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets method.
     *
     * @param method the method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Is seasonal boolean.
     *
     * @return the boolean
     */
    public boolean isSeasonal() {
        return isSeasonal;
    }

    /**
     * Sets seasonal.
     *
     * @param seasonal the seasonal
     */
    public void setSeasonal(boolean seasonal) {
        isSeasonal = seasonal;
    }

    /**
     * Gets seasonal frequency.
     *
     * @return the seasonal frequency
     */
    public int getSeasonalFrequency() {
        return seasonalFrequency;
    }

    /**
     * Sets seasonal frequency.
     *
     * @param seasonalFrequency the seasonal frequency
     */
    public void setSeasonalFrequency(int seasonalFrequency) {
        this.seasonalFrequency = seasonalFrequency;
    }

    /**
     * Gets channelId.
     *
     * @return the channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * Sets channelId.
     *
     * @param channelId the channelId
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * Gets productId.
     *
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets productId.
     *
     * @param productId the productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Gets history start date.
     *
     * @return the history start date
     */
    public LocalDate getHistoryStartDate() {
        return historyStartDate;
    }

    /**
     * Sets history start date.
     *
     * @param historyStartDate the history start date
     */
    public void setHistoryStartDate(LocalDate historyStartDate) {
        this.historyStartDate = historyStartDate;
    }

    /**
     * Gets history end date.
     *
     * @return the history end date
     */
    public LocalDate getHistoryEndDate() {
        return historyEndDate;
    }

    /**
     * Sets history end date.
     *
     * @param historyEndDate the history end date
     */
    public void setHistoryEndDate(LocalDate historyEndDate) {
        this.historyEndDate = historyEndDate;
    }

    /**
     * Gets forecast start date.
     *
     * @return the forecast start date
     */
    public LocalDate getForecastStartDate() {
        return forecastStartDate;
    }

    /**
     * Sets forecast start date.
     *
     * @param forecastStartDate the forecast start date
     */
    public void setForecastStartDate(LocalDate forecastStartDate) {
        this.forecastStartDate = forecastStartDate;
    }

    /**
     * Gets forecast end date.
     *
     * @return the forecast end date
     */
    public LocalDate getForecastEndDate() {
        return forecastEndDate;
    }

    /**
     * Sets forecast end date.
     *
     * @param forecastEndDate the forecast end date
     */
    public void setForecastEndDate(LocalDate forecastEndDate) {
        this.forecastEndDate = forecastEndDate;
    }

    @Override
    public String toString() {
        return "ForecastInput{" +
                "method='" + method + '\'' +
                ", isSeasonal=" + isSeasonal +
                ", seasonalFrequency=" + seasonalFrequency +
                ", channelId='" + channelId + '\'' +
                ", productId='" + productId + '\'' +
                ", historyStartDate=" + historyStartDate +
                ", historyEndDate=" + historyEndDate +
                ", forecastStartDate=" + forecastStartDate +
                ", forecastEndDate=" + forecastEndDate +
                ", daysAheadOfHistoryStartDate=" + daysAheadOfHistoryStartDate +
                ", productSalesDetails=" + unitSalesDetails +
                '}';
    }

}
