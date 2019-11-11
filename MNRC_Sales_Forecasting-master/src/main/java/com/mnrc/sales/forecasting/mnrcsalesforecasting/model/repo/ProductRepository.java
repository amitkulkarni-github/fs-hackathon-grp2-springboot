package com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Product repository.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
