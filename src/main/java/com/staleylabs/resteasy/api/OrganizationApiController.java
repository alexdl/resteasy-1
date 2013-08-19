package com.staleylabs.resteasy.api;

import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.service.OrganizationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * API's for organization entities in the application.
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
     * API call that is used to get a response of all users found in the application.
     *
     * @return JSON list of {@link com.staleylabs.resteasy.dto.UserTO} objects.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UserTO> getAllOrganizations() {
        LOGGER.debug("Requesting all organizations!");

        return organizationService.getAllOrganizations();
    }

}
