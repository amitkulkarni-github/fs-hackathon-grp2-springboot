package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastResponse;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.processors.ForecastProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Forecasting controller.
 */
@RestController
public class ForecastingController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * The Forecast processor.
     */
    @Autowired
    ForecastProcessor forecastProcessor;

    /**
     * This method needs the following inputs:
     * The below are mandatory inputs:
     * {
     * "channel":"Online",
     * "product":"Phone",
     * "historyStartDate":"2018-01-01",
     * "historyEndDate": "2019-01-01",
     * "forecastStartDate":"2020-02-02",
     * "forecastEndDate":"2021-01-01"
     * }
     * private String method  -  One of the options : first-order | differenced-first-order| SES
     * This value must be used by the history service to get the values from mongo db
     * private String channel -
     * private String product;
     * private String date;
     * // The Product Details must be formed like this:
     * <p>
     * List of
     * [
     * The Forecast only needs the sales values to form a double array.
     * If the rest of the values are not there it will have the
     * forecast populated and send the result
     * <p>
     * private String channel;
     * private String product;
     * private String date;
     * private double sales;
     * private double forecast;
     * <p>
     * ]
     *
     * @param forecastInput the forecast input
     * @return response entity
     * @throws Exception the exception
     */
    @RequestMapping(value = "/forecast", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ForecastResponse> addResource(@RequestBody ForecastInput forecastInput) throws Exception {
        return new ResponseEntity<ForecastResponse>(forecastProcessor.processInput(forecastInput), HttpStatus.OK);
    }

}
