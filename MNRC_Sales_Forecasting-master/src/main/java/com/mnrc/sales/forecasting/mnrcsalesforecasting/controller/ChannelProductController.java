package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.Channel;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.ChannelProduct;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo.ChannelProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Channel product controller.
 */
@RestController
public class ChannelProductController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * The Channel product repository.
     */
    @Autowired
    ChannelProductRepository channelProductRepository;

    /**
     * Gets all channel products.
     *
     * @return the all channel products
     */
    @RequestMapping(value = "/channelProducts", method = RequestMethod.GET)
    public List<ChannelProduct> getAllChannelProducts() {
        LOG.info("Getting all Channels and Products.");
        return channelProductRepository.findAll();
    }
}
