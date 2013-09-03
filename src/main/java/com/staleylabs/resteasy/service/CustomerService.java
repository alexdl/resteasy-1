package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.domain.Customer;
import com.staleylabs.resteasy.dto.CustomerTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;

/**
 * Service layer class that is to be used for all things regarding a customer DTO or entity object. This includes CRUD
 * operations on the customer objects.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

public interface CustomerService {

    /**
     * Creates a new customer and persists them to the data source.
     *
     * @param customer Object that will be persisted in the data source.
     * @return {@link String} {@link Customer#customerID} to be used throughout the application.
     */
    String createNewCustomer(Customer customer) throws InsufficientInformationException;

    /**
     * Obtains an existing customer in the application.
     *
     * @param customerID {@link String} representation of the customer's ID.
     * @return {@link CustomerTO} object that corresponds to the ID provided. If a customer was not found, a {@code null}
     *         value will be returned.
     */
    CustomerTO getExistingCustomer(String customerID);

}
