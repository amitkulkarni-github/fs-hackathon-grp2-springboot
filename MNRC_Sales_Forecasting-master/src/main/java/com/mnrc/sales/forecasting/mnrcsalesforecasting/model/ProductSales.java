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
@Document(collection = "productsales")
public class ProductSales {
    @Id
    private String salesId;
    @Field("CHANNEL")
    private String channel;
    @Field("PRODUCT")
    private String product;
    @Field("DAY")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Field("UNITS")
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
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets channel.
     *
     * @param channel the channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(String product) {
        this.product = product;
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
                ", channel='" + channel + '\'' +
                ", product='" + product + '\'' +
                ", date=" + date +
                ", units=" + units +
                '}';
    }
}
