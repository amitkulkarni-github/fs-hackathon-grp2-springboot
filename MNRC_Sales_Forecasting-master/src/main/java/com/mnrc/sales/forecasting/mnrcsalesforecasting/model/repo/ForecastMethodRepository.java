package com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ForecastMethod;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Forecast method repository.
 */
@Repository
public interface ForecastMethodRepository extends MongoRepository<ForecastMethod, String> {
}
