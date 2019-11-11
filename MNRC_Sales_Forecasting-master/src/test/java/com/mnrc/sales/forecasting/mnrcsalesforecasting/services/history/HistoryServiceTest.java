package com.mnrc.sales.forecasting.mnrcsalesforecasting.services.history;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ProductSales;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.forecast.UnitDetails;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo.ProductSalesRepository;
import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryServiceTest {

    @MockBean
    ProductSalesRepository productSalesRepository;

    @Autowired
    HistoryService historyService;

    @Test
    public void getHistoryList() {
        LocalDate startDate = LocalDate.parse("2018-01-01");
        LocalDate endDate = LocalDate.parse("2018-12-01");
        String channelId = "1";
        String productId = "2";
        List<ProductSales> productSalesHistory = new ArrayList<>();
        ProductSales productSales = new ProductSales();
        productSales.setChannelId("1");
        productSales.setProductId("2");
        productSales.setSalesId("1");
        productSalesHistory.add(productSales);
        Mockito.when(productSalesRepository.findSalesByDateRange(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class), Mockito.anyString(), Mockito.anyString())).thenReturn(productSalesHistory);
        List<ProductSales> result =historyService.getHistoryList(startDate,endDate,channelId,productId);
        assertTrue(result.size() >0 );
        assertTrue(result.get(0).getChannelId().equalsIgnoreCase("1"));
        assertTrue(result.get(0).getProductId().equalsIgnoreCase("2"));
        assertTrue(result.get(0).getSalesId().equalsIgnoreCase("1"));
    }

    @Test
    public void getUnitSalesDetails() {
        LocalDate startDate = LocalDate.parse("2018-01-01");
        LocalDate endDate = LocalDate.parse("2018-12-01");
        String channelId = "1";
        String productId = "2";
        List<ProductSales> productSalesHistory = new ArrayList<>();
        ProductSales productSales = new ProductSales();
        productSales.setChannelId("1");
        productSales.setProductId("2");
        productSales.setSalesId("1");
        productSalesHistory.add(productSales);
        Mockito.when(productSalesRepository.findSalesByDateRange(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class), Mockito.anyString(), Mockito.anyString())).thenReturn(productSalesHistory);
        List<UnitDetails> result =historyService.getUnitSalesDetails(startDate,endDate,channelId,productId);
        assertTrue(result.get(0).getChannelId().equalsIgnoreCase("1"));
        assertTrue(result.get(0).getProductId().equalsIgnoreCase("2"));
        assertTrue(result.get(0).getSalesId().equalsIgnoreCase("1"));
    }
}