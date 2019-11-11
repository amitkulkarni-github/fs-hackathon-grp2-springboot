package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ForecastingException;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastResponse;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitSalesDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.processors.ForecastProcessor;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.util.ForecastValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForecastingControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @MockBean
    ForecastProcessor forecastProcessor;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void addResource_v001() throws Exception {
        Map<String, String> f = new HashMap<>();
        f.put("historyStartDate", "2018-01-01");
        f.put("historyEndDate", "2018-01-01");
        f.put("forecastStartDate", "2019-01-01");
        f.put("forecastEndDate", "2019-04-01");
        mvc.perform(MockMvcRequestBuilders.post("/forecast")
                .content(toJsonString(f)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResolvedException().getMessage().equalsIgnoreCase("V001:Forecast history date not valid. Forecast date cannot be before 2014-01-01");

    }

    @Test
    public void addResource_v002() throws Exception {
        Map<String, String> f = new HashMap<>();
        f.put("historyStartDate", "2018-01-01");
        f.put("historyEndDate", "2018-01-01");
        f.put("forecastStartDate", "2019-01-01");
        f.put("forecastEndDate", "2019-04-01");
        mvc.perform(MockMvcRequestBuilders.post("/forecast/")
                .content(toJsonString(f)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn().getResolvedException().getMessage().equalsIgnoreCase("V002:Forecast history date not valid. History duration must be minimum of 180 Days");
    }

    @Test
    public void addResource_HttpStatus_Ok() throws Exception {
        Map<String, String> f = new HashMap<>();
        f.put("historyStartDate", "2018-01-01");
        f.put("historyEndDate", "2018-12-01");
        f.put("forecastStartDate", "2019-01-01");
        f.put("forecastEndDate", "2019-04-01");

        ForecastResponse forecastResponse = new ForecastResponse();
        forecastResponse.setHistoryDetails(new ArrayList<>());
        forecastResponse.setForecastMap(new HashMap<>());
        mvc.perform(MockMvcRequestBuilders.post("/forecast")
                .content(toJsonString(f
                )).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    protected byte[] toJsonString(Map<String,String> i) throws Exception {
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(i).getBytes();
    }

}
