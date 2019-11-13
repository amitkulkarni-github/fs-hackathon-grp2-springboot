#  Multinational Retail Corporation

This is a Java Spring Boot Service open source. It provides services to get

1) Products (Resource path is /products)
2) Channles (Resource path is /channels)
3) Products in Channel (Resource path is /channelProducts)
4) ARIMA Forecasting method variances (Resource path is /forecastMethods)
5) History data for given date range (Resource path is /historysales)
6) Forecast for given channel, prodcut and forecast range(resource path is /forecast)

How to Use
---

In order to use this service for forecasting data we need to input the below payload for endpoint /forecast.

{
  "channelId": "string",
  "daysAheadOfHistoryStartDate": 0,
  "forecastEndDate": "yyyy-MM-dd",
  "forecastPeriod": 0,
  "forecastStartDate": "yyyy-MM-dd",
  "historyEndDate": "yyyy-MM-dd",
  "historyStartDate": "yyyy-MM-dd",
  "method": "string",
  "productId": "string",
  "seasonal": true,
  "seasonalFrequency": 0,
  "unitSalesDetails": {
    "forecastUnitDetails": [
      {
        "channelId": "string",
        "date": "yyyy-MM-dd",
        "lowers": 0,
        "productId": "string",
        "salesId": "string",
        "units": 0,
        "uppers": 0
      }
    ],
    "historyUnitDetails": [
      {
        "channelId": "string",
        "date": "yyyy-MM-dd",
        "lowers": 0,
        "productId": "string",
        "salesId": "string",
        "units": 0,
        "uppers": 0
      }
    ]
  }
}

How to Build
---
This library uses Maven as its build tool.

```java
// Compile the source code of the project.
mvn compile

// To generate javadocs
mvn javadoc:javadoc

// To generate a site for the current project
mvn site

// Take the compiled code and package it
mvn package

// Install the package into the local repository, which can be used as a dependency in other projects locally.
mvn install
```
How to Deploy
---

Above generated jar file you can take it and run any server having JVM with below command

java -jar mnrc-sales-forecasting-0.0.1-SNAPSHOT.jar


Dependencies
---

The library has the following dependencies:
```

1) ARIMA Library from below location
https://github.com/Workday/timeseries-forecast.git
2) MONGO DB is Hosted on Database-as-a-Service by mLab



```
