package com.staleylabs.resteasy.api;

import com.staleylabs.resteasy.dto.OrganizationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.OrganizationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * APIs for organization entities in the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/18/13)
 */

@Controller
@RequestMapping(value = "/api/organizations")
public class OrganizationApiController {

    private static final Logger LOGGER = Logger.getLogger(OrganizationApiController.class.getName());

    @Autowired
    private OrganizationService organizationService;

    /**
     * API call that is used to get a response of all organizations found in the application.
     *
     * @return JSON list of {@link com.staleylabs.resteasy.dto.OrganizationTO} objects.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(OK)
    @ResponseBody
    public List<OrganizationTO> getAllOrganizations() {
        LOGGER.debug("Requesting all organizations!");

        return organizationService.getAllOrganizations();
    }

    /**
     * API call that is used to get a response of the organization found in the application.
     *
     * @return JSON list of {@link com.staleylabs.resteasy.dto.OrganizationTO} object.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    @ResponseStatus(OK)
    @ResponseBody
    public OrganizationTO getUserOrganizations() {
        LOGGER.debug("Requesting user's organizations!");

        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return organizationService.getUserOrganizations(user);
    }

    /**
     * Service used to add an organization to the application.
     *
     * @param organization {@link OrganizationTO} entity used to generate the new organization.
     * @throws InsufficientInformationException
     *          Thrown if there missing information that is required to be there.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseStatus(CREATED)
    public void addOrganization(@RequestBody OrganizationTO organization) throws InsufficientInformationException {
        LOGGER.debug("Requesting to add Organization to application.");

        organizationService.createOrganization(organization);
    }
}
