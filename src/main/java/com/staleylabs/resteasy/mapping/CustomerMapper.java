package com.staleylabs.resteasy.mapping;

import com.staleylabs.resteasy.domain.Customer;
import com.staleylabs.resteasy.dto.CustomerTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Custom implementation of mapping between {@link Customer} entity and {@link com.staleylabs.resteasy.dto.CustomerTO}
 * front end entities.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Component
public class CustomerMapper extends ModelMapper {

    private ModelMapper modelMapper;

    public CustomerMapper() {
        modelMapper = new ModelMapper();
    }

    /**
     * Transformation method of a customer entity to a front end transfer entity form.
     *
     * @param customer {@link Customer} entity that is retrieved from the back end of the application.
     * @return {@link CustomerTO} object that represented the incoming customer object.
     */
    public CustomerTO transformCustomer(Customer customer) {
        return modelMapper.map(customer, CustomerTO.class);
    }

    /**
     * Transformation method of a customer transfer object to the back end entity form.
     *
     * @param customer {@link CustomerTO} object that is retrieved from the front end of the application.
     * @return {@link Customer} entity that represented the incoming customer object.
     */
    public Customer transformCustomer(CustomerTO customer) {
        return modelMapper.map(customer, Customer.class);
    }

}
