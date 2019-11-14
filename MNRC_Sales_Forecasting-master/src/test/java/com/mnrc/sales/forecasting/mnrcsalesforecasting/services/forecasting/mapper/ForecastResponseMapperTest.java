package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ForecastingException;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ArimaResponse;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ForecastResponseMapperTest {

    @InjectMocks
    ForecastResponseMapper forecastResponseMapper;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getArimaResponse() {
        ArimaResponse arimaResponse = new ArimaResponse();
         double[] forecastArray = new double[] { 1, 2,3,4,5,6,7,8,9,10};
         double[] uppers  = new double[] { 1, 2,3,4,5,6,7,8,9,10};
         double[] lowers  = new double[] { 1, 2,3,4,5,6,7,8,9,10};
        arimaResponse.setForecastArray(forecastArray);
        arimaResponse.setLowers(lowers);
        arimaResponse.setUppers(uppers);
        arimaResponse.setMaxNormalizedVariance(2);
        arimaResponse.setRmse(2);
        ForecastInput forecastInput = new ForecastInput();
        forecastInput.setDaysAheadOfHistoryStartDate(1);
        forecastInput.setForecastEndDate(LocalDate.parse("2018-01-01"));
        forecastInput.setForecastStartDate(LocalDate.parse("2018-01-01"));
        forecastInput.setChannelId("1");
        forecastInput.setProductId("2");
        List<UnitDetails> unitDetailsList = forecastResponseMapper.getArimaResponse(arimaResponse,forecastInput);
        assertTrue(unitDetailsList.size() > 0);
    }

    @Test
    public void getArimaResponseException1() {
        ArimaResponse arimaResponse = new ArimaResponse();
        double[] forecastArray = new double[] { 1};
        double[] uppers  = new double[] { 1, 2,3,4,5,6,7,8,9,10};
        double[] lowers  = new double[] { 1, 2,3,4,5,6,7,8,9,10};
        arimaResponse.setForecastArray(forecastArray);
        arimaResponse.setLowers(lowers);
        arimaResponse.setUppers(uppers);
        arimaResponse.setMaxNormalizedVariance(2);
        arimaResponse.setRmse(2);
        ForecastInput forecastInput = new ForecastInput();
        forecastInput.setDaysAheadOfHistoryStartDate(2);
        forecastInput.setForecastEndDate(LocalDate.parse("2018-01-01"));
        forecastInput.setForecastStartDate(LocalDate.parse("2018-01-01"));
        forecastInput.setChannelId("1");
        forecastInput.setProductId("2");
        List<UnitDetails> unitDetailsList = forecastResponseMapper.getArimaResponse(arimaResponse,forecastInput);
        assertTrue(unitDetailsList.size() == 0);

    }


    @Test
    public void getArimaResponseException2() {
        ArimaResponse arimaResponse = new ArimaResponse();
        double[] forecastArray = new double[] { 1, 2,3,4,5,6,7,8,9,10};
        double[] uppers  = new double[] { 1};
        double[] lowers  = new double[] { 1, 2,3,4,5,6,7,8,9,10};
        arimaResponse.setForecastArray(forecastArray);
        arimaResponse.setLowers(lowers);
        arimaResponse.setUppers(uppers);
        arimaResponse.setMaxNormalizedVariance(2);
        arimaResponse.setRmse(2);
        ForecastInput forecastInput = new ForecastInput();
        forecastInput.setDaysAheadOfHistoryStartDate(2);
        forecastInput.setForecastEndDate(LocalDate.parse("2018-01-01"));
        forecastInput.setForecastStartDate(LocalDate.parse("2018-01-01"));
        forecastInput.setChannelId("1");
        forecastInput.setProductId("2");
        List<UnitDetails> unitDetailsList = forecastResponseMapper.getArimaResponse(arimaResponse,forecastInput);
        assertTrue(unitDetailsList.size() == 8);

    }


    @Test
    public void getArimaResponseException3() {
        ArimaResponse arimaResponse = new ArimaResponse();
        double[] forecastArray = new double[] { 1, 2,3,4,5,6,7,8,9,10};
        double[] lowers  = new double[] { 1};
        double[] uppers  = new double[] { 1, 2,3,4,5,6,7,8,9,10};
        arimaResponse.setForecastArray(forecastArray);
        arimaResponse.setLowers(lowers);
        arimaResponse.setUppers(uppers);
        arimaResponse.setMaxNormalizedVariance(2);
        arimaResponse.setRmse(2);
        ForecastInput forecastInput = new ForecastInput();
        forecastInput.setDaysAheadOfHistoryStartDate(2);
        forecastInput.setForecastEndDate(LocalDate.parse("2018-01-01"));
        forecastInput.setForecastStartDate(LocalDate.parse("2018-01-01"));
        forecastInput.setChannelId("1");
        forecastInput.setProductId("2");
        List<UnitDetails> unitDetailsList = forecastResponseMapper.getArimaResponse(arimaResponse,forecastInput);
        assertTrue(unitDetailsList.size() == 8);
    }
}