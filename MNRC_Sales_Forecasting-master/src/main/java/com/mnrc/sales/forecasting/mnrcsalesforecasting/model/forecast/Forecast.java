package com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast;

import java.util.List;

public class Forecast {

    private String methodId;
    private List<UnitDetails> data;

    public Forecast(String methodId, List<UnitDetails> data) {
        this.methodId = methodId;
        this.data = data;
    }

    public Forecast() {
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public List<UnitDetails> getData() {
        return data;
    }

    public void setData(List<UnitDetails> data) {
        this.data = data;
    }
}
