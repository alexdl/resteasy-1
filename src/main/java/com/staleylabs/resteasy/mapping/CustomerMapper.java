package com.staleylabs.resteasy.mapping;

import com.staleylabs.resteasy.domain.Customer;
import com.staleylabs.resteasy.dto.CustomerTO;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Custom implementation of mapping between {@link Customer} entity and {@link com.staleylabs.resteasy.dto.CustomerTO}
 * front end entities.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Component
public class CustomerMapper extends ModelMapper {

    private static final Logger LOGGER = Logger.getLogger(CustomerMapper.class);

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

    /**
     * Transforms a collection of {@link Customer} objects into {@link CustomerTO} objects for front end consumption.
     *
     * @param customers {@link List} of {@link Customer} entities to be transformed.
     * @return {@link Collection} of {@link CustomerTO} objects.
     */
    public Collection<CustomerTO> transformCustomers(Collection<Customer> customers) {
        List<CustomerTO> toList = new ArrayList<>(customers.size());

        for (Customer customer : customers) {
            toList.add(transformCustomer(customer));
        }

        LogMF.debug(LOGGER, "Transformed {0} customer entity objects into TO objects.", toList.size());

        return toList;
    }

    /**
     * Transforms a collection of {@link Customer} objects into {@link CustomerTO} objects for front end consumption.
     *
     * @param customers {@link List} of {@link Customer} entities to be transformed.
     * @return {@link Collection} of {@link CustomerTO} objects.
     */
    public Collection<Customer> transformCustomers(List<CustomerTO> customers) {
        List<Customer> toList = new ArrayList<>(customers.size());

        for (CustomerTO customer : customers) {
            toList.add(transformCustomer(customer));
        }

        LogMF.debug(LOGGER, "Transformed {0} customer entity objects into TO objects.", toList.size());

        return toList;
    }
}
