package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting.mapper;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ArimaRequest;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitSalesDetails;
import com.workday.insights.timeseries.arima.struct.ArimaParams;
import org.springframework.stereotype.Component;

/**
 * The type Forecast request mapper.
 */
@Component
public class ForecastRequestMapper {

    /**
     * Get arima params arima request.
     *
     * @param method            the method
     * @param isSeasonal        the is seasonal
     * @param seasonalFrequency the seasonal frequency
     * @param salesDetails      the sales details
     * @return the arima request
     */
    public ArimaRequest getArimaParams(String method, boolean isSeasonal, int seasonalFrequency,
                                       UnitSalesDetails salesDetails){
        ArimaRequest arimaRequest = new ArimaRequest();
        ArimaParams params = null;
        arimaRequest.setHistoryArray(salesDetails.getHistoryUnitDetails().stream().map(e -> e.getUnits()).mapToDouble(Double::doubleValue).toArray());
        if(!isSeasonal){
            if("first-order".equalsIgnoreCase(method)){
                params = new ArimaParams(1,0,0,0,0,0,0);
            } else if("random-walk".equalsIgnoreCase(method)){
                throw new UnsupportedOperationException("Not implemented");
            }else if ("differenced-first-order".equalsIgnoreCase(method)){
                params = new ArimaParams(1,1,0,0,0,0,0);
            } else if("SES".equalsIgnoreCase(method)){
                params = new ArimaParams(0,1,1,0,0,0,0);
            } else if ("damped-trend-linear-exponential-smoothing".equalsIgnoreCase(method)){
                params = new ArimaParams(1,1,2,0,0,0,0);
            }
        } else{
            if("first-order".equalsIgnoreCase(method)){
                params = new ArimaParams(0,0,0,1,0,0,seasonalFrequency);  // m - monthly
            } else if("random-walk".equalsIgnoreCase(method)){
                throw new UnsupportedOperationException("Not implemented");
            }else if ("differenced-first-order".equalsIgnoreCase(method)){
                params = new ArimaParams(0,0,0,1,1,0,seasonalFrequency);  // m - monthly
            } else if("SES".equalsIgnoreCase(method)){
                params = new ArimaParams(0,0,0,0,1,1,seasonalFrequency);  // m - monthly
            } else if ("damped-trend-linear-exponential-smoothing".equalsIgnoreCase(method)){
                params = new ArimaParams(0,0,0,1,1,2,seasonalFrequency);
            }
        }
        arimaRequest.setArimaParams(params);
        return arimaRequest;
    }
}
