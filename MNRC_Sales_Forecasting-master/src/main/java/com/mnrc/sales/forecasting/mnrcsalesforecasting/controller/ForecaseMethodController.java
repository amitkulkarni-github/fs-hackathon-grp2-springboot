package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ForecastingException;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ChannelProduct;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ForecastMethod;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo.ChannelProductRepository;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo.ForecastMethodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ForecaseMethodController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    ForecastMethodRepository forecastMethodRepository;

    @RequestMapping(value = "/forecastMethods", method = RequestMethod.GET)
    public List<ForecastMethod> getAllForecastMethods() {
        LOG.info("Getting all Forecast methods.");
        return forecastMethodRepository.findAll();
    }
}
