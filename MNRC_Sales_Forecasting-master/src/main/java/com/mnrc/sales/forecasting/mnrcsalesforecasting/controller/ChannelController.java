package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;

import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.Channel;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo.ChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Channel controller.
 */
@RestController
public class ChannelController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * The Channel repository.
     */
    @Autowired
    ChannelRepository channelRepository;

    /**
     * Gets all channels.
     *
     * @return the all channels
     */
    @RequestMapping(value = "/channels", method = RequestMethod.GET)
    public List<Channel> getAllChannels() {
        LOG.info("Getting all Channels.");
        return channelRepository.findAll();
    }
}
