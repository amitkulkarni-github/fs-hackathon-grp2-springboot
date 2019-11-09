package com.mnrc.sales.forecasting.mnrcsalesforecasting.controller;


import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.User;
import com.mnrc.sales.forecasting.mnrcsalesforecasting.model.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequestMapping(value = "/")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Add new users user.
     *
     * @param user the user
     * @return the user
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) {
        LOG.info("Saving user.");
        return userRepository.save(user);
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        LOG.info("Getting all users.");
        return userRepository.findAll();
    }

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID: {}.", userId);
        return userRepository.findById(userId).orElse(null);
    }
}
