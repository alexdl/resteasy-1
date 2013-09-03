package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.domain.Customer;
import com.staleylabs.resteasy.dto.CustomerTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Proxy between the actual implementation of {@link CustomerService} and the calling method.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Service("customerService")
public class CustomerServiceProxy implements CustomerService {

    @Autowired
    private CustomerService customerServiceImpl;

    @Override
    public String createNewCustomer(Customer customer) throws InsufficientInformationException {
        if (StringUtils.isNotBlank(customer.getFirstName()) && StringUtils.isNotBlank(customer.getLastName())
                && customer.getCustomerContact() != null && StringUtils.isNotBlank(customer.getEmailAddress())) {
            return customerServiceImpl.createNewCustomer(customer);
        } else {
            throw new InsufficientInformationException("Customer information was incomplete.");
        }
    }

    @Override
    public CustomerTO getExistingCustomer(String customerID) {
        if (StringUtils.isNotBlank(customerID)) {
            return customerServiceImpl.getExistingCustomer(customerID);
        }

        return null;
    }
}
