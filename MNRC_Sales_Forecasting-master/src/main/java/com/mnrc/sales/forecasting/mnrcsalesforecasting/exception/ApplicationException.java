package com.mnrc.sales.forecasting.mnrcsalesforecasting.exception;

import java.io.Serializable;

public class ApplicationException implements Serializable {
    private String exceptionCode;
    private String exceptionMessage;

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
                "exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }
}
