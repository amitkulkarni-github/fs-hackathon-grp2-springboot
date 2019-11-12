package com.mnrc.sales.forecasting.mnrcsalesforecasting.util;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ForecastingException;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ForecastValidatorTest {

    ForecastValidator forecastValidator = new ForecastValidator();

    @Test(expected = ForecastingException.class)
    public void validateForecastRequest_V001() {
        ForecastInput forecastInput  = new ForecastInput();
        forecastInput.setHistoryStartDate(LocalDate.parse("2010-01-01"));
        forecastValidator.validateForecastRequest(forecastInput);
    }


    @Test( expected =  ForecastingException.class)
    public void validateForecastRequest_V002() {
        ForecastInput forecastInput  = new ForecastInput();
        forecastInput.setHistoryStartDate(LocalDate.parse("2018-01-01"));
        forecastInput.setHistoryEndDate(LocalDate.parse("2018-01-01"));
        forecastValidator.validateForecastRequest(forecastInput);
    }
}