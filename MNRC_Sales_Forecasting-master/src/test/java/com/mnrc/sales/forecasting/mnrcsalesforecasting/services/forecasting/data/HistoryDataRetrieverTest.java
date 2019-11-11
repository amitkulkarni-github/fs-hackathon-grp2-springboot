package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.data;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.history.HistoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HistoryDataRetrieverTest {

    @InjectMocks
    HistoryDataRetriever historyDataRetriever;

    @Mock
    HistoryService historyService;

    List<UnitDetails> datalist;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        datalist = new ArrayList<>();
        UnitDetails unitDetails = new UnitDetails();
        datalist.add(unitDetails);
        Mockito.when(historyService.getUnitSalesDetails(LocalDate.parse("2018-01-01"),LocalDate.parse("2018-12-01"),"1","2")).thenReturn(datalist);
    }

    @Test
    public void getHistoryForecastDetails() {
        ForecastInput forecastInput =  new ForecastInput();
        forecastInput.setForecastStartDate(LocalDate.parse("2019-01-01"));
        forecastInput.setForecastEndDate(LocalDate.parse("2019-12-01"));
        forecastInput.setHistoryStartDate(LocalDate.parse("2018-01-01"));
        forecastInput.setHistoryEndDate(LocalDate.parse("2018-12-01"));
        forecastInput.setProductId("2");
        forecastInput.setChannelId("1");
        historyDataRetriever.getHistoryForecastDetails(forecastInput);
        assertTrue(forecastInput.getUnitSalesDetails().getHistoryUnitDetails().size() >0);
    }

    @Test
    public void populateHistoryDetails() {
    }
}