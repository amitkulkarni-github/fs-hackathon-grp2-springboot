package com.mnrc.sales.forecasting.mnrcsalesforecasting.util;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ForecastingException;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ForecastValidator {

    public void validateForecastRequest(ForecastInput forecastInput) throws ForecastingException {
        if(forecastInput.getHistoryStartDate().isBefore(LocalDate.parse("2014-01-01"))){
            throw new ForecastingException("V001:Forecast history date not valid. Forecast date cannot be before 2014-01-01");
        }
        if(ChronoUnit.DAYS.between(forecastInput.getHistoryStartDate(), forecastInput.getHistoryEndDate()) < 180 ){
            throw new ForecastingException("V002:Forecast history date not valid. History duration must be minimum of 180 Days");
        }
    }
}
