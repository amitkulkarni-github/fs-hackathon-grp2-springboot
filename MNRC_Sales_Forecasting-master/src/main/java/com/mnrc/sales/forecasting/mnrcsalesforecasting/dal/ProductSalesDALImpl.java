package com.mnrc.sales.forecasting.mnrcsalesforecasting.dal;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ProductSales;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * The type Product sales dal.
 */
@Repository
public class ProductSalesDALImpl implements ProductSalesDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ProductSales> findSalesByDateRange(LocalDate startDate, LocalDate endDate, String channelId, String productId){
        Query query = new Query();
        ObjectId channelObjID = new ObjectId(channelId);
        ObjectId productObjID = new ObjectId(productId);
        query.addCriteria(Criteria.where("day" +
                "").lte(endDate).gte(startDate).andOperator(Criteria.where("channel" +
                "").is(channelObjID).andOperator(Criteria.where("product" +
                "").is(productObjID))));
      // query.addCriteria(Criteria.where("UNITS").lt(60).gt(2));
        //query.limit(10);
        return mongoTemplate.find(query, ProductSales.class);
    }
}
