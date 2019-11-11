package com.mnrc.sales.forecasting.mnrcsalesforecasting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Forecasting exception.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ForecastingException extends RuntimeException{

    /**
     * Instantiates a new Forecasting exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ForecastingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForecastingException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
