package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ForecastMethod;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.Product;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo.ForecastMethodRepository;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts() {
        LOG.info("Getting all Products.");
        return new ResponseEntity<List<Product>>(productRepository.findAll(), HttpStatus.OK);
    }
}
