package com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast;

import java.util.Arrays;

/**
 * The type Arima response.
 */
public class ArimaResponse {
    private double[] forecastArray;
    private double[] uppers;
    private double[] lowers;
    private double rmse;
    private double maxNormalizedVariance;

    /**
     * Get forecast array double [ ].
     *
     * @return the double [ ]
     */
    public double[] getForecastArray() {
        return forecastArray;
    }

    /**
     * Sets forecast array.
     *
     * @param forecastArray the forecast array
     */
    public void setForecastArray(double[] forecastArray) {
        this.forecastArray = forecastArray;
    }

    /**
     * Get uppers double [ ].
     *
     * @return the double [ ]
     */
    public double[] getUppers() {
        return uppers;
    }

    /**
     * Sets uppers.
     *
     * @param uppers the uppers
     */
    public void setUppers(double[] uppers) {
        this.uppers = uppers;
    }

    /**
     * Get lowers double [ ].
     *
     * @return the double [ ]
     */
    public double[] getLowers() {
        return lowers;
    }

    /**
     * Sets lowers.
     *
     * @param lowers the lowers
     */
    public void setLowers(double[] lowers) {
        this.lowers = lowers;
    }

    /**
     * Gets rmse.
     *
     * @return the rmse
     */
    public double getRmse() {
        return rmse;
    }

    /**
     * Sets rmse.
     *
     * @param rmse the rmse
     */
    public void setRmse(double rmse) {
        this.rmse = rmse;
    }

    /**
     * Gets max normalized variance.
     *
     * @return the max normalized variance
     */
    public double getMaxNormalizedVariance() {
        return maxNormalizedVariance;
    }

    /**
     * Sets max normalized variance.
     *
     * @param maxNormalizedVariance the max normalized variance
     */
    public void setMaxNormalizedVariance(double maxNormalizedVariance) {
        this.maxNormalizedVariance = maxNormalizedVariance;
    }

    @Override
    public String toString() {
        return "ArimaResponse{" +
                "forecastArray=" + Arrays.toString(forecastArray) +
                ", uppers=" + Arrays.toString(uppers) +
                ", lowers=" + Arrays.toString(lowers) +
                ", rmse=" + rmse +
                ", maxNormalizedVariance=" + maxNormalizedVariance +
                '}';
    }
}
