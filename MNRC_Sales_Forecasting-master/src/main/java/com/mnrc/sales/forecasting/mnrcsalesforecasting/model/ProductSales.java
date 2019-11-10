package com.mnrc.sales.forecasting.mnrcsalesforecasting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;

/**
 * The type Product sales.
 */
@Document(collection = "productSales")
public class ProductSales {
    @Id
    private String salesId;
    @Field("channel")
    private String channelId;
    @Field("product")
    private String productId;
    @Field("day")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Field("units")
    private double units;

    /**
     * Gets sales id.
     *
     * @return the sales id
     */
    public String getSalesId() {
        return salesId;
    }

    /**
     * Sets sales id.
     *
     * @param salesId the sales id
     */
    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    /**
     * Gets channel.
     *
     * @return the channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * Sets channel.
     *
     * @param channelId the channel
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * Gets productId.
     *
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets product.
     *
     * @param productId the product
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets units.
     *
     * @return the units
     */
    public double getUnits() {
        return units;
    }

    /**
     * Sets units.
     *
     * @param units the units
     */
    public void setUnits(double units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "ProductSales{" +
                "salesId='" + salesId + '\'' +
                ", channelId='" + channelId + '\'' +
                ", productId='" + productId + '\'' +
                ", date=" + date +
                ", units=" + units +
                '}';
    }
}