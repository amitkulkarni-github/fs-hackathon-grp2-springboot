package com.mnrc.sales.forecasting.mnrcsalesforecasting.dal;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ProductSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProductSalesDALImpl implements ProductSalesDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ProductSales> findSalesByDateRange(Date startDate, Date endDate){
        Query query = new Query();
        query.addCriteria(Criteria.where("DAY").lte(endDate).gte(startDate));
      // query.addCriteria(Criteria.where("UNITS").lt(60).gt(2));
        //query.limit(10);
        return mongoTemplate.find(query, ProductSales.class);
    }
}
