package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.dao.CustomerDao;
import com.staleylabs.resteasy.domain.Customer;
import com.staleylabs.resteasy.dto.CustomerTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.mapping.CustomerMapper;
import com.staleylabs.resteasy.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        log.info("Added new customer to application " + customer.toString());

        return customer.getCustomerID();
    }

    @Override
    public CustomerTO getExistingCustomer(String customerID) {
        Customer customer = customerDao.findOne(customerID);

        return customerMapper.transformCustomer(customer);
    }
}
