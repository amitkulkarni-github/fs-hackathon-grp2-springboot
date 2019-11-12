package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.*;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper.ForecastRequestMapper;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper.ForecastResponseMapper;
import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import com.workday.insights.timeseries.arima.struct.ForecastResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ForecastingServiceTest {

    /**
     * The Forecast request mapper.
     */
    @MockBean
    ForecastRequestMapper forecastRequestMapper;

    /**
     * The Forecast response mapper.
     */
    @MockBean
    ForecastResponseMapper forecastResponseMapper;

    @MockBean
    ArimaInvoker arimaInvoker;

    @Autowired
    ForecastingService forecastingService;

    @Mock
    ForecastResult forecastResult;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getForecastDataAsync() throws ExecutionException, InterruptedException {

        ForecastInput forecastInput = new ForecastInput();
        forecastInput.setForecastPeriod(2);
        forecastInput.setMethod("5dc75e0ac0bd39e99bf3d69f");
        forecastInput.setSeasonal(true);
        forecastInput.setSeasonalFrequency(1);
        ForecastResponse forecastResponse = new ForecastResponse();
        UnitSalesDetails unitSalesDetails = new UnitSalesDetails();
        forecastInput.setUnitSalesDetails(unitSalesDetails);
        ArimaParams params = new ArimaParams(1, 0, 0, 0, 0, 0, 0);
        ArimaRequest arimaRequest = new ArimaRequest();
        arimaRequest.setHistoryArray(new double[]{1, 2, 3});
        arimaRequest.setArimaParams(params);
        Mockito.when(forecastRequestMapper.getArimaParams(Mockito.anyString(),
                Mockito.anyBoolean(), Mockito.anyInt(), Mockito.any())).thenReturn(arimaRequest);
        Mockito.when(arimaInvoker.getForecastResult(arimaRequest, forecastInput)).thenReturn(forecastResult);
        Mockito.when(forecastResult.getForecast()).thenReturn(new double[]{});
        Mockito.when(forecastResult.getForecastUpperConf()).thenReturn(new double[]{});
        Mockito.when(forecastResult.getForecastLowerConf()).thenReturn(new double[]{});
        Mockito.when(forecastResult.getRMSE()).thenReturn(2.0);
        Mockito.when(forecastResult.getMaxNormalizedVariance()).thenReturn(2.0);
        List<UnitDetails> unitDetails = new ArrayList<>();
        UnitDetails unitDetails1 = new UnitDetails();
        unitDetails1.setUnits(1.0);
        unitDetails.add(unitDetails1);
        Mockito.when(forecastResponseMapper.getArimaResponse(Mockito.any(ArimaResponse.class), Mockito.any(ForecastInput.class))).thenReturn(unitDetails);
        CompletableFuture<Boolean> cp = forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dc75e0ac0bd39e99bf3d69f",false,0);
        assertTrue(cp.get());
        CompletableFuture<Boolean> cp1 = forecastingService.getForecastDataAsync(forecastInput,forecastResponse,"5dc75e0ac0bd39e99bf3d69f",true,1);
        assertTrue(cp1.get());
    }

    @Test
    public void getForecastData() throws ExecutionException, InterruptedException {
        ForecastInput forecastInput = new ForecastInput();
        forecastInput.setForecastPeriod(2);
        forecastInput.setMethod("5dc75e0ac0bd39e99bf3d69f");
        forecastInput.setSeasonal(true);
        forecastInput.setSeasonalFrequency(1);
        ForecastResponse forecastResponse = new ForecastResponse();
        UnitSalesDetails unitSalesDetails = new UnitSalesDetails();
        forecastInput.setUnitSalesDetails(unitSalesDetails);
        ArimaParams params = new ArimaParams(1, 0, 0, 0, 0, 0, 0);
        ArimaRequest arimaRequest = new ArimaRequest();
        arimaRequest.setHistoryArray(new double[]{1, 2, 3});
        arimaRequest.setArimaParams(params);
        Mockito.when(forecastRequestMapper.getArimaParams(forecastInput.getMethod(),
                forecastInput.isSeasonal(), forecastInput.getSeasonalFrequency(), forecastInput.getUnitSalesDetails())).thenReturn(arimaRequest);
        Mockito.when(arimaInvoker.getForecastResult(arimaRequest, forecastInput)).thenReturn(forecastResult);
        Mockito.when(forecastResult.getForecast()).thenReturn(new double[]{});
        Mockito.when(forecastResult.getForecastUpperConf()).thenReturn(new double[]{});
        Mockito.when(forecastResult.getForecastLowerConf()).thenReturn(new double[]{});
        Mockito.when(forecastResult.getRMSE()).thenReturn(2.0);
        Mockito.when(forecastResult.getMaxNormalizedVariance()).thenReturn(2.0);
        List<UnitDetails> unitDetails = new ArrayList<>();
        UnitDetails unitDetails1 = new UnitDetails();
        unitDetails1.setUnits(1.0);
        unitDetails.add(unitDetails1);
        Mockito.when(forecastResponseMapper.getArimaResponse(Mockito.any(ArimaResponse.class), Mockito.any(ForecastInput.class))).thenReturn(unitDetails);
        List<UnitDetails> forecastData = forecastingService.getForecastData(forecastInput);
        assertTrue(forecastData.size() > 0);
    }


}