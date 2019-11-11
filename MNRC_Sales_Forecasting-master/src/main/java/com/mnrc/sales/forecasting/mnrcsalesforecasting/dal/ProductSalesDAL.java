package com.mnrc.sales.forecasting.mnrcsalesforecasting.dal;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ProductSales;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * The interface Product sales dal.
 */
@Repository
public interface ProductSalesDAL {
    /**
     * Find sales by date range list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    List<ProductSales> findSalesByDateRange(LocalDate startDate, LocalDate endDate, String channelId, String productId);
}
