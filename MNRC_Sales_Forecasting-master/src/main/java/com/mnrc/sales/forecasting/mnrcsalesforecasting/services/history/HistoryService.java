package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.history;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ProductSales;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitSalesDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo.ProductSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type History service.
 */
@Service
public class HistoryService {

    /**
     * The Product sales repository.
     */
    @Autowired
    ProductSalesRepository productSalesRepository;

    /**
     * Get history list list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    public List<ProductSales> getHistoryList(LocalDate startDate, LocalDate endDate, String channelId, String productId){
        List<ProductSales> productSalesHistory = productSalesRepository.findSalesByDateRange(startDate, endDate, channelId, productId);
        return productSalesHistory;
    }

    /**
     * Get unit sales details list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    public List<UnitDetails> getUnitSalesDetails(LocalDate startDate, LocalDate endDate, String channelId, String productId){
        List<ProductSales> productSalesHistory = productSalesRepository.findSalesByDateRange(startDate, endDate, channelId, productId);
        return productSalesHistory.stream().filter(Objects::nonNull).map(productSales -> {
            UnitDetails unitDetails = new UnitDetails();
            unitDetails.setProductId(productSales.getProductId());
            unitDetails.setChannelId(productSales.getChannelId());
            unitDetails.setSalesId(productSales.getSalesId());
            unitDetails.setDate(productSales.getDate());
            unitDetails.setUnits(productSales.getUnits());
            return unitDetails;
        }).collect(Collectors.toList());
    }

}
