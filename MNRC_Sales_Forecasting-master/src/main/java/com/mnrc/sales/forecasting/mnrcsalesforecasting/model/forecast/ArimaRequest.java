package com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast;

import com.workday.insights.timeseries.arima.struct.ArimaParams;

import java.util.Arrays;

/**
 * The type Arima request.
 */
public class ArimaRequest {
    private ArimaParams arimaParams;
    private double[] historyArray;

    /**
     * Gets arima params.
     *
     * @return the arima params
     */
    public ArimaParams getArimaParams() {
        return arimaParams;
    }

    /**
     * Sets arima params.
     *
     * @param arimaParams the arima params
     */
    public void setArimaParams(ArimaParams arimaParams) {
        this.arimaParams = arimaParams;
    }

    /**
     * Get history array double [ ].
     *
     * @return the double [ ]
     */
    public double[] getHistoryArray() {
        return historyArray;
    }

    /**
     * Sets history array.
     *
     * @param historyArray the history array
     */
    public void setHistoryArray(double[] historyArray) {
        this.historyArray = historyArray;
    }

    @Override
    public String toString() {
        return "ForecastRequest{" +
                "arimaParams=" + arimaParams +
                ", historyArray=" + Arrays.toString(historyArray) +
                '}';
    }
}
