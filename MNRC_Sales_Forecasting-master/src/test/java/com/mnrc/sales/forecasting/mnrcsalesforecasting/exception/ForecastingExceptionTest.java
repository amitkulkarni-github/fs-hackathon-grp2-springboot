package com.mnrc.sales.forecasting.mnrcsalesforecasting.exception;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ForecastingExceptionTest {

    @Test
    public void getMessage() {
        ForecastingException forecastingException = new ForecastingException("test");
        assertTrue(forecastingException.getMessage().equalsIgnoreCase("test"));
    }

    @Test(expected = ForecastingException.class)
    public void constructor() {
        ForecastingException forecastingException = new ForecastingException("test", new Exception());
        throw forecastingException;
    }
}