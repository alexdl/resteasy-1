package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.dao.OrganizationDao;
import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.domain.user.RegisteringUser;
import com.staleylabs.resteasy.service.ContactService;
import com.staleylabs.resteasy.service.OrganizationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the OrganizationService.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/1/13)
 * @see OrganizationService
 */

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private static final Logger log = Logger.getLogger(OrganizationServiceImpl.class.getName());

    @Autowired
    private ContactService contactService;

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public Organization generateOrganizationFromRegisteringUser(RegisteringUser user) {
        log.debug("Generating organization bean for first time user.");

        Organization organization = new Organization();

        organization.setOrganizationName(user.getOrganizationName());
        organization.setOrganizationContactInformation(contactService.generateOrganizationObjectFromRegisteringUser(user));

        organizationDao.save(organization);

        log.debug("Completed generating organization bean for first time user.");

        return organization;
    }

    @Override
    public String getIdFromOrganizationName(String organizationName) {
        log.debug("Obtaining the ID for organization named " + organizationName);

        Organization organization = organizationDao.getOrganizationByOrganizationName(organizationName);

        if(organization == null) {
            log.info("No organization, " + organizationName + ", found in data source.");
            return null;
        }

        String organizationID = organization.getId();

        log.debug("Found the ID of the given organization as " + organizationID);
        return organizationID;
    }
}
