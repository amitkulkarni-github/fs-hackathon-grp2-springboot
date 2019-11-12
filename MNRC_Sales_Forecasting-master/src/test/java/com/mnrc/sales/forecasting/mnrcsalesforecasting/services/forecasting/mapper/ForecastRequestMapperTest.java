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
        String method = "5dc75e0ac0bd39e99bf3d69f";
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
        method = "5dc75e0ac0bd39e99bf3d69b";
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());
        method = "5dc75e0ac0bd39e99bf3d699";
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());

        method ="5dc75e0ac0bd39e99bf3d69d";
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());

        method = "5dca45b5ba26446724ed00ed";
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

        method = "5dca4586ba26446724ed00ec";
        isSeasonal = true;
        seasonalFrequency=1;
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());
        method = "5dca4554ba26446724ed00eb";
        isSeasonal = true;
        seasonalFrequency=1;
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());

        method ="5dca45cfba26446724ed00ee";
        isSeasonal = true;
        seasonalFrequency=1;
        arimaRequest = forecastRequestMapper.getArimaParams(method, isSeasonal, seasonalFrequency, salesDetails);
        assertNotNull(arimaRequest);
        assertNotNull(arimaRequest.getArimaParams());

    }
}