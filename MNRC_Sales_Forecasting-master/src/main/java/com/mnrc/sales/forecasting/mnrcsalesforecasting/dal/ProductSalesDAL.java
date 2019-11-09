package com.mnrc.sales.forecasting.mnrcsalesforecasting.dal;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ProductSales;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductSalesDAL {
    public List<ProductSales> findSalesByDateRange(Date startDate, Date endDate);
}
