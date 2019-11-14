package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ApplicationException;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ForecastingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    /**
     * Handles the Exception thrown by Controller
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ForecastingException.class)
    public ResponseEntity<Object> exception(ForecastingException exception) {
        ApplicationException applicationException = new ApplicationException();
        applicationException.setExceptionCode("INVALID_REQUEST");
        applicationException.setExceptionMessage(exception.getMessage());
        return new ResponseEntity<>(applicationException, HttpStatus.BAD_REQUEST);
    }
}
