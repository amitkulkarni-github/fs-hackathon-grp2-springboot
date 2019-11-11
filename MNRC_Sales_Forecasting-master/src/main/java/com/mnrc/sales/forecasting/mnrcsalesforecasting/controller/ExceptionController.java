package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ForecastingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = ForecastingException.class)
    public ResponseEntity<Object> exception(ForecastingException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
