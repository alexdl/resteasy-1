package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.beans.forms.RegisteringUser;
import com.staleylabs.resteasy.dao.OrganizationDao;
import com.staleylabs.resteasy.dao.UserDao;
import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.domain.User;
import com.staleylabs.resteasy.dto.OrganizationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;
import com.staleylabs.resteasy.mapping.OrganizationMapper;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.ContactService;
import com.staleylabs.resteasy.service.OrganizationService;
import com.staleylabs.resteasy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

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

    @Inject
    private ContactService contactService;

    @Inject
    private OrganizationDao organizationDao;

    @Inject
    private OrganizationMapper organizationMapper;

    @Inject
    private UserDao userDao;

    @Inject
    private UserService userServiceImpl;

    @Override
    public Organization generateOrganizationFromRegisteringUser(RegisteringUser user) {
        log.debug("Generating organization bean for first time user.");

        Organization organization = new Organization();

        organization.setOrganizationName(user.getOrganizationName());
        organization.setOrganizationContact(contactService.generateOrganizationObjectFromRegisteringUser(user));

        organizationDao.save(organization);

        log.debug("Completed generating organization bean for first time user.");

        return organization;
    }

    @Override
    public String getIdFromOrganizationName(String organizationName) {
        log.debug("Obtaining the ID for organization named " + organizationName);

        Organization organization = organizationDao.getOrganizationByOrganizationName(organizationName);

        if (organization == null) {
            log.info("No organization, " + organizationName + ", found in data source.");
            return null;
        }

        String organizationID = organization.getId();

        log.debug("Found the ID of the given organization as " + organizationID);
        return organizationID;
    }

    @Override
    public List<OrganizationTO> getAllOrganizations() {
        return organizationMapper.transformOrganizations(organizationDao.findAll());
    }

    @Override
    public void createOrganization(OrganizationTO organization) throws InsufficientInformationException {
        Organization persistOrganization = organizationMapper.transformOrganizationTO(organization);

        organizationDao.save(persistOrganization);
    }

    @Override
    public OrganizationTO getUserOrganizations(SecureRestEasyUser user) {
        Organization organization = organizationDao.findOne(user.getOrganizationID());

        return organizationMapper.transformOrganization(organization);
    }

    @Override
    public void deleteOrganization(String organizationId) throws InsufficientPrivilegeException {
        log.info("Removing organization " + organizationId + " from the application.");

        // First remove all of the organization's associated users.
        List<User> users = userDao.findByOrganizationId(organizationId);

        for (User user : users) {
            userServiceImpl.updateUserOrganizations(user.getId(), null);
        }

        // Finally, delete the organization from the application.
        organizationDao.delete(organizationId);

        log.info("Removed the organization from the application.");
    }
}
