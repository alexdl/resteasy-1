package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.domain.user.RegisteringUser;
import com.staleylabs.resteasy.dto.OrganizationTO;

import java.util.List;

/**
 * Service interface used for all things that deal with the organization bean. This would be creating, obtaining,
 * deleting, and manipulating a given {@link Organization} object.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/1/13)
 */

public interface OrganizationService {

    /**
     * Method that generates an {@link Organization} object from an incoming new user.
     *
     * @param user User that has registered with the application for the first time.
     * @return {@link Organization} object with all properties found during registration.
     */
    Organization generateOrganizationFromRegisteringUser(RegisteringUser user);

    /**
     * Grabs the ID of the given organization name.
     *
     * @param organizationName The name of a given organization in {@link String} form.
     * @return ID of a given organization.
     */
    String getIdFromOrganizationName(String organizationName);

    /**
     * Obtains all of the organizations found in the application.
     *
     * @return {@link List} of {@link OrganizationTO} objects.
     */
    List<OrganizationTO> getAllOrganizations();
}
