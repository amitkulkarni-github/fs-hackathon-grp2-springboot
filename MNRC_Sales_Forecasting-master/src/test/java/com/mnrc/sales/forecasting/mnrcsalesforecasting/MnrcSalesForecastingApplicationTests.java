package com.mnrc.sales.forecasting.mnrcsalesforecasting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The type Mnrc sales forecasting application tests.
 */
@SpringBootTest
class MnrcSalesForecastingApplicationTests {

    /**
     * Context loads.
     */
    @Test
	void contextLoads() {
	}


    @Test
    public void main() {
        MnrcSalesForecastingApplication.main(new String[]{"" +
                "--spring.main.web-environment=false",
                "--spring.autoconfigure.exclude=another",
                "--spring.server.port=9090"
        });
    }
}
