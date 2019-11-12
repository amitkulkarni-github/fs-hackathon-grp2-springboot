package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.processors;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastResponse;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitSalesDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.ForecastingService;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.data.HistoryDataRetriever;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForecastProcessorTest {

    @MockBean
    HistoryDataRetriever historyDataRetriever;

    /**
     * The Forecasting service.
     */
    @MockBean
    ForecastingService forecastingService;

    @Autowired
    ForecastProcessor forecastProcessor;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void processInput_allMethods() {
        ForecastInput forecastInput = new ForecastInput();
        ForecastResponse forecastResponse = new ForecastResponse();
        String forecastMethod ="5dc75e0ac0bd39e99bf3d69f";
        boolean isSeasonal = false;
        int seasonalFrequency =0;
        forecastInput.setUnitSalesDetails(new UnitSalesDetails());
        forecastInput.getUnitSalesDetails().setHistoryUnitDetails(new ArrayList<>());
        CompletableFuture<Boolean> compFuture = CompletableFuture.supplyAsync(() -> {
            forecastResponse.setHistoryDetails(new ArrayList<>());
            forecastResponse.getHistoryDetails().add(new UnitDetails());
            return true;
        });
        Mockito.when(forecastingService.getForecastDataAsync(Mockito.any(ForecastInput.class),Mockito.any(ForecastResponse.class),Mockito.anyString(), Mockito.anyBoolean(),Mockito.anyInt())).thenReturn(compFuture);
        forecastProcessor.processInput(forecastInput);
        assertTrue(forecastResponse.getHistoryDetails().size() >0);
    }

    @Test
    public void processInput_first_order_method() {
        ForecastInput forecastInput = new ForecastInput();
        forecastInput.setMethod("5dc75e0ac0bd39e99bf3d69f");
        ForecastResponse forecastResponse = new ForecastResponse();
        forecastInput.setUnitSalesDetails(new UnitSalesDetails());
        forecastInput.getUnitSalesDetails().setHistoryUnitDetails(new ArrayList<>());
        List<UnitDetails> unitDetailsList = new ArrayList<>();
        UnitDetails unitDetails = new UnitDetails();
        unitDetails.setUnits(2.0);
        unitDetailsList.add(unitDetails);
        Mockito.when(forecastingService.getForecastData(Mockito.any(ForecastInput.class))).thenReturn(unitDetailsList);
        forecastResponse.setHistoryDetails(new ArrayList<>());
        forecastResponse=forecastProcessor.processInput(forecastInput);
        assertTrue(forecastResponse.getHistoryDetails().size() == 0);
    }

}