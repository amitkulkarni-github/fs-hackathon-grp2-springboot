package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.exception.ForecastingException;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * The type Forecast response mapper.
 */
@Component
public class ForecastResponseMapper {

    /**
     * getDataDoubleList
     *
     * @param doubleArray
     * @param forecastInput
     * @return
     */
    public List<Double> getDataDoubleList(double[] doubleArray, ForecastInput forecastInput){
        List<Double> forecastListFull = DoubleStream.of(doubleArray).boxed().collect(Collectors.toList());
        List<Double> forcastList = new ArrayList<>();
        if (forecastListFull.size() > forecastInput.getDaysAheadOfHistoryStartDate()) {
            for(int i=forecastInput.getDaysAheadOfHistoryStartDate(); i < forecastListFull.size(); i++){
                forcastList.add(forecastListFull.get(i));
            }
        }
        return forcastList;
    }

    /**
     * Gets arima response.
     *
     * @param arimaResponse the arima response
     * @param forecastInput the forecast input
     * @return the arima response
     * @throws Exception the exception
     */
    public List<UnitDetails> getArimaResponse(ArimaResponse arimaResponse,
                                              ForecastInput forecastInput) throws ForecastingException {
        List<UnitDetails> list = null;
        if (null != arimaResponse) {
            List<Double> forecastList = getDataDoubleList(arimaResponse.getForecastArray(),forecastInput);
            List<Double> uppersList = getDataDoubleList(arimaResponse.getUppers(),forecastInput);
            List<Double> lowerList = getDataDoubleList(arimaResponse.getLowers(),forecastInput);

            LocalDate start = forecastInput.getForecastStartDate();
            LocalDate stop = forecastInput.getForecastEndDate();
            LocalDate foreCastStartDate = start;
            AtomicInteger i = new AtomicInteger(1);
            AtomicInteger j = new AtomicInteger(0);
            list = forecastList.stream().map(aDouble -> {
                UnitDetails unitDetails = new UnitDetails();
                unitDetails.setSalesId(Integer.toString(i.getAndIncrement()));
                unitDetails.setUnits(Math.abs(aDouble));
                unitDetails.setChannelId(forecastInput.getChannelId());
                unitDetails.setProductId(forecastInput.getProductId());
                if(CollectionUtils.isNotEmpty(uppersList)) {
                    unitDetails.setUppers(Math.abs(uppersList.get(j.get())));
                }
                if(CollectionUtils.isNotEmpty(lowerList)) {
                    unitDetails.setLowers(Math.abs(lowerList.get(j.get())));
                }
                unitDetails.setDate(foreCastStartDate.plusDays(j.getAndIncrement()));
                return unitDetails;
            }).collect(Collectors.toList());

        }else {
            list = new ArrayList<>();
        }
        return list;
    }
}
