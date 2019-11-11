package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ArimaRequest;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitSalesDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ForecastRequestMapperTest {

    @InjectMocks
    ForecastRequestMapper forecastRequestMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getArimaParams() {
        String method = "first-order";
        boolean isSeasonal = false;
        int seasonalFrequency = 0;
        UnitSalesDetails salesDetails = new UnitSalesDetails();
        List<UnitDetails> historyUnitDetails = new ArrayList<>();
        UnitDetails unitDetails = new UnitDetails();
        unitDetails.setUnits(Double.parseDouble("2.0"));
        historyUnitDetails.add(unitDetails);
        salesDetails.setHistoryUnitDetails(historyUnitDetails);
        ArimaRequest arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());
        method = "random-walk";
        try {
            arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
        method = "differenced-first-order";
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());
        method = "SES";
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());

        method ="damped-trend-linear-exponential-smoothing";
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());

        method = "first-order";
        isSeasonal = true;
        seasonalFrequency=1;
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());

        method = "random-walk";
        isSeasonal = true;
        seasonalFrequency=1;

        try {
            arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }

        method = "differenced-first-order";
        isSeasonal = true;
        seasonalFrequency=1;
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());
        method = "SES";
        isSeasonal = true;
        seasonalFrequency=1;
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());

        method ="damped-trend-linear-exponential-smoothing";
        isSeasonal = true;
        seasonalFrequency=1;
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());

    }
}