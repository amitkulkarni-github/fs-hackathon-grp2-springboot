package com.mnrc.sales.forecasting.mnrcsalesforecasting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MnrcSalesForecastingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MnrcSalesForecastingApplication.class, args);
	}

}
