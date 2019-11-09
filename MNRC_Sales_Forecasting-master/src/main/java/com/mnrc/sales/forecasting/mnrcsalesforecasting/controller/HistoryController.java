package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ProductSales;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.history.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * The type History controller.
 */
@RestController
public class HistoryController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * The History service.
     */
    @Autowired
    HistoryService historyService;

    /**
     * Gets sales by date range.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the sales by date range
     */
    @RequestMapping(value = "/historysales", method = RequestMethod.GET)
    public ResponseEntity<List<ProductSales>> getSalesByDateRange(@RequestParam("startdate")
                                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                  @RequestParam("enddate")  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        LOG.info("Getting history.");
        return new ResponseEntity<List<ProductSales>>(historyService.getHistoryList(startDate,endDate), HttpStatus.OK);
    }
}
