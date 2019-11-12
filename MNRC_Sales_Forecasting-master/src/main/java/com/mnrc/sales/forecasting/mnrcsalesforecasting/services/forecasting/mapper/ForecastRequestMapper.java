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
            if("5dc75e0ac0bd39e99bf3d69f".equalsIgnoreCase(method)){  //first-order
                params = new ArimaParams(1,0,0,0,0,0,0);
            } else if("random-walk".equalsIgnoreCase(method)){
                throw new UnsupportedOperationException("Not implemented");
            }else if ("5dc75e0ac0bd39e99bf3d69b".equalsIgnoreCase(method)){  //differenced-first-order
                params = new ArimaParams(1,1,0,0,0,0,0);
            } else if("5dc75e0ac0bd39e99bf3d699".equalsIgnoreCase(method)){  //SES
                params = new ArimaParams(0,1,1,0,0,0,0);
            } else if ("5dc75e0ac0bd39e99bf3d69d".equalsIgnoreCase(method)){ //damped-trend-linear-exponential-smoothing
                params = new ArimaParams(1,1,2,0,0,0,0);
            }
        } else{
            if("5dca45b5ba26446724ed00ed".equalsIgnoreCase(method)){ //first-order
                params = new ArimaParams(0,0,0,1,0,0,seasonalFrequency);  // m - monthly
            } else if("random-walk".equalsIgnoreCase(method)){
                throw new UnsupportedOperationException("Not implemented");
            }else if ("5dca4586ba26446724ed00ec".equalsIgnoreCase(method)){ //differenced-first-order
                params = new ArimaParams(0,0,0,1,1,0,seasonalFrequency);  // m - monthly
            } else if("5dca4554ba26446724ed00eb".equalsIgnoreCase(method)){  //SES
                params = new ArimaParams(0,0,0,0,1,1,seasonalFrequency);  // m - monthly
            } else if ("5dca45cfba26446724ed00ee".equalsIgnoreCase(method)){ //damped-trend-linear-exponential-smoothing
                params = new ArimaParams(0,0,0,1,1,2,seasonalFrequency);
            }
        }
        arimaRequest.setArimaParams(params);
        return arimaRequest;
    }
}
