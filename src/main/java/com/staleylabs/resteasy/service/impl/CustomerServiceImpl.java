package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.dao.CustomerDao;
import com.staleylabs.resteasy.domain.Customer;
import com.staleylabs.resteasy.dto.CustomerTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;
import com.staleylabs.resteasy.mapping.CustomerMapper;
import com.staleylabs.resteasy.service.CustomerService;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of the {@link CustomerService}.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = Logger.getLogger(CustomerServiceImpl.class.getName());

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String createNewCustomer(Customer customer) throws InsufficientInformationException {
        customer = customerDao.save(customer);

        LogMF.info(log, "Customer with ID {0} created successfully.", customer.getCustomerID());
        return customer.getCustomerID();
    }

    @Override
    public CustomerTO getExistingCustomer(String customerID) {
        Customer customer = customerDao.findOne(customerID);

        return customerMapper.transformCustomer(customer);
    }

    @Override
    public void createNewCustomer(CustomerTO customerTO) throws InsufficientInformationException {
        log.info("Creating new user from TO object.");

        // Transform and persist the new user.
        createNewCustomer(customerMapper.transformCustomer(customerTO));
    }

    @Override
    public Collection<CustomerTO> getAllCustomers() throws InsufficientPrivilegeException {
        return customerMapper.transformCustomers(customerDao.findAll());
    }

    @Override
    public void removeCustomer(String customerID) {
        // TODO: Remove customer from the application.
    }
}
