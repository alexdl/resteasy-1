package com.staleylabs.resteasy.api;

import com.staleylabs.resteasy.dto.OrganizationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.OrganizationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * APIs for organization entities in the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/18/13)
 */

@Controller
@RequestMapping(value = "/api/organization")
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

    /**
     * Service used to delete a given organization from the application.
     *
     * @param organizationId {@link String} form of the ID from the organization that will be removed.
     * @param response       {@link HttpServletResponse} object that will be used to return to the requester.
     * @throws IOException Occurs if there is an issue with sending the error with the response.
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{organizationId}")
    @ResponseStatus(NO_CONTENT)
    public void removeOrganization(@PathVariable String organizationId, HttpServletResponse response) throws IOException {
        LOGGER.debug("API call to remove " + organizationId + " received.");

        try {
            organizationService.deleteOrganization(organizationId);
        } catch (InsufficientPrivilegeException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not allowed to perform requested operation.");
        }
    }
}
