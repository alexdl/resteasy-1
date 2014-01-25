package com.staleylabs.resteasy.api;

import com.staleylabs.resteasy.dto.CustomerTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;
import com.staleylabs.resteasy.service.CustomerService;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import static org.springframework.http.HttpStatus.*;

/**
 * Customer level service API methods that can be used for all CRUD operations of a customer object.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/7/13)
 */

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerApiController {

    private static final Logger LOGGER = Logger.getLogger(CustomerApiController.class);

    @Inject
    private CustomerService customerService;

    /**
     * Creates a new customer in the application.
     *
     * @param customerTO Representation of the user that is wished to store in the application.
     * @param response   {@link HttpServletResponse} object used to send back error responses if needed.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(CREATED)
    public void addCustomer(@RequestBody CustomerTO customerTO, HttpServletResponse response) throws IOException {
        LOGGER.debug("Calling the addCustomer API...");

        try {
            customerService.createNewCustomer(customerTO);
        } catch (InsufficientInformationException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Obtains a single instance of a customer in the application.
     *
     * @param customerID {@link String} ID of the user that will be returned to the front end.
     * @param response   {@link HttpServletResponse} object used to send back error responses if needed.
     * @throws IOException Occurs if there is an issue sending back an error in the response.
     */
    @RequestMapping(value = "/{customerID}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(OK)
    public CustomerTO getCustomerByID(@PathVariable String customerID, HttpServletResponse response) throws IOException {
        LOGGER.debug("Calling the getCustomerByID API...");

        if (StringUtils.isBlank(customerID)) {
            response.sendError(HttpStatus.SC_BAD_REQUEST, "Customer's ID was blank. Must contain ID of customer.");
        }

        return customerService.getExistingCustomer(customerID);
    }

    /**
     * Service method used to get all of the customers found in the current application.
     *
     * @param response {@link HttpServletResponse} object used to send back error responses if needed.
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(OK)
    public Collection<CustomerTO> getAllCustomers(HttpServletResponse response) throws IOException {
        LOGGER.debug("Calling the getAllCustomers API...");

        try {
            return customerService.getAllCustomers();
        } catch (InsufficientPrivilegeException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }

        return Collections.emptyList();
    }

    /**
     * Service method used to remove a particular customer from the application.
     *
     * @param customerID {@link String} ID of the customer that should be removed from the application.
     * @param response   {@link HttpServletResponse} object used to send back error responses if needed.
     */
    @RequestMapping(value = "/delete/{customerID}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
    @ResponseStatus(NO_CONTENT)
    public void deleteCustomer(@PathVariable String customerID, HttpServletResponse response) {
        LOGGER.debug("Calling the deleteCustomer API...");

        customerService.removeCustomer(customerID);
    }
}
