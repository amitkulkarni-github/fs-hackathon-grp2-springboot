package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.forecasting;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ArimaRequest;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.ForecastInput;
import com.workday.insights.timeseries.arima.Arima;
import com.workday.insights.timeseries.arima.struct.ForecastResult;
import org.springframework.stereotype.Component;

@Component
public class ArimaInvoker {

    public ForecastResult getForecastResult(ArimaRequest arimaRequest, ForecastInput forecastInput){
        return Arima.forecast_arima(arimaRequest.getHistoryArray(),
                forecastInput.getForecastPeriod(), arimaRequest.getArimaParams());
    }
}
