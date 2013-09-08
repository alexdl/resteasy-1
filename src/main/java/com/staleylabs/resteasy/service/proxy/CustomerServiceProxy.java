package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.domain.Customer;
import com.staleylabs.resteasy.dto.CustomerTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Proxy between the actual implementation of {@link CustomerService} and the calling method.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Service("customerService")
public class CustomerServiceProxy implements CustomerService {

    private static final Logger LOGGER = Logger.getLogger(CustomerServiceProxy.class);

    @Autowired
    private CustomerService customerServiceImpl;

    @Override
    public String createNewCustomer(Customer customer) throws InsufficientInformationException {
        if (StringUtils.isNotBlank(customer.getFirstName())
                && StringUtils.isNotBlank(customer.getLastName())
                && customer.getCustomerContact() != null
                && StringUtils.isNotBlank(customer.getEmailAddress())) {
            return customerServiceImpl.createNewCustomer(customer);
        } else {
            LOGGER.warn("Customer was missing vital information used to create a new instance.");

            throw new InsufficientInformationException("Customer information was incomplete.");
        }
    }

    @Override
    public CustomerTO getExistingCustomer(String customerID) {
        if (StringUtils.isNotBlank(customerID)) {
            return customerServiceImpl.getExistingCustomer(customerID);
        }

        LOGGER.info("When obtaining an existing customer's information, the customer ID was blank.");

        return null;
    }

    @Override
    public void createNewCustomer(CustomerTO customerTO) throws InsufficientInformationException {
        if (StringUtils.isNotBlank(customerTO.getFirstName())
                && StringUtils.isNotBlank(customerTO.getLastName())
                && StringUtils.isNotBlank(customerTO.getEmailAddress())) {
            customerServiceImpl.createNewCustomer(customerTO);
        } else {
            LOGGER.warn("Customer was missing vital information used to create a new instance.");
            throw new InsufficientInformationException("Customer information was incomplete.");
        }
    }

    @Override
    public Collection<CustomerTO> getAllCustomers() throws InsufficientPrivilegeException {
        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return customerServiceImpl.getAllCustomers();
            }
        }
        throw new InsufficientPrivilegeException();
    }

    @Override
    public void removeCustomer(String customerID) {
        // TODO: Logic to check organization ID.

        customerServiceImpl.removeCustomer(customerID);
    }
}
