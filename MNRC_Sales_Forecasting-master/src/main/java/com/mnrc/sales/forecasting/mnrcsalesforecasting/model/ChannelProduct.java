package com.mnrc.sales.forecasting.mnrcsalesforecasting.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The type Channel product.
 */
@Document(collection = "channelProducts")
public class ChannelProduct {
    @Id
    private String id;
    @Field("channelId")
    private String channelId;
    @Field("productId")
    private String productId;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets channel id.
     *
     * @return the channel id
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * Sets channel id.
     *
     * @param channelId the channel id
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
}
