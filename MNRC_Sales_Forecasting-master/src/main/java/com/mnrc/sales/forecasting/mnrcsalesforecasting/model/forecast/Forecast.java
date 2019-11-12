package com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast;

import java.util.ArrayList;
import java.util.List;

public class Forecast {

    private String methodName;
    private List<UnitDetails> data;

    public Forecast(String methodName, List<UnitDetails> data) {
        this.methodName = methodName;
        this.data = data;
    }

    public Forecast() {
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<UnitDetails> getData() {
        return data;
    }

    public void setData(List<UnitDetails> data) {
        this.data = data;
    }
}
