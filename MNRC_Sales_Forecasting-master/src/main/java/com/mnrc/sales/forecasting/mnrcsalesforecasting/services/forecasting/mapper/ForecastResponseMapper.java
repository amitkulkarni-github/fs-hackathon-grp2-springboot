package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * The type Forecast response mapper.
 */
@Component
public class ForecastResponseMapper {

    /**
     * Gets arima response.
     *
     * @param arimaResponse the arima response
     * @param forecastInput the forecast input
     * @return the arima response
     * @throws Exception the exception
     */
    public List<UnitDetails> getArimaResponse(ArimaResponse arimaResponse,
                                              ForecastInput forecastInput) throws Exception {
        List<UnitDetails> list = null;
        List<Double> forecastListFull = DoubleStream.of(arimaResponse.getForecastArray()).boxed().collect(Collectors.toList());
        List<Double> forcastList = new ArrayList<>();
        if (forecastListFull.size() > forecastInput.getDaysAheadOfHistoryStartDate()) {
            for(int i=forecastInput.getDaysAheadOfHistoryStartDate(); i < forecastListFull.size(); i++){
                forcastList.add(forecastListFull.get(i));
            }
        } else {
            throw new Exception("Something is wrong");
        }

        List<Double> uppersListFull = DoubleStream.of(arimaResponse.getUppers()).boxed().collect(Collectors.toList());
        List<Double> uppersList = new ArrayList<>();
        if (uppersListFull.size() > forecastInput.getDaysAheadOfHistoryStartDate()) {
            for(int i=forecastInput.getDaysAheadOfHistoryStartDate(); i < uppersListFull.size(); i++){
                uppersList.add(uppersListFull.get(i));
            }
        } else {
            throw new Exception("Something is wrong");
        }

        List<Double> lowerListFull = DoubleStream.of(arimaResponse.getLowers()).boxed().collect(Collectors.toList());
        List<Double> lowerList = new ArrayList<>();
        if (lowerListFull.size() > forecastInput.getDaysAheadOfHistoryStartDate()) {
            for(int i=forecastInput.getDaysAheadOfHistoryStartDate(); i < lowerListFull.size(); i++){
                lowerList.add(lowerListFull.get(i));
            }
        } else {
            throw new Exception("Something is wrong");
        }

        if (null != arimaResponse) {
            LocalDate start = forecastInput.getForecastStartDate();
            LocalDate stop = forecastInput.getForecastEndDate();
            LocalDate foreCastStartDate = start;
            AtomicInteger i = new AtomicInteger(1);
            AtomicInteger j = new AtomicInteger(0);
            AtomicInteger k = new AtomicInteger(0);
            list = forcastList.stream().map(aDouble -> {
                UnitDetails unitDetails = new UnitDetails();
                unitDetails.setSalesId(Integer.toString(i.getAndIncrement()));
                unitDetails.setUnits(aDouble);
                unitDetails.setChannel(forecastInput.getChannel());
                unitDetails.setProduct(forecastInput.getProduct());
                unitDetails.setDate(foreCastStartDate);
                unitDetails.setUppers(uppersList.get(j.getAndIncrement()));
                unitDetails.setLowers(lowerList.get(k.getAndIncrement()));
                foreCastStartDate.plusDays(1);
                return unitDetails;
            }).collect(Collectors.toList());

        }
        return list;
    }
}
