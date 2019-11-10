package com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ChannelProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelProductRepository extends MongoRepository<ChannelProduct, String> {
}
