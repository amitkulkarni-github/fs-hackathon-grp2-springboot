package com.mnrc.sales.forecasting.mnrcsalesforecasting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Mnrc sales forecasting application.
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableSwagger2
public class MnrcSalesForecastingApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		SpringApplication.run(MnrcSalesForecastingApplication.class, args);
	}

}
