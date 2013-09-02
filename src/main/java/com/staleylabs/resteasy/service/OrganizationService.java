package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.beans.forms.RegisteringUser;
import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.dto.OrganizationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.security.SecureRestEasyUser;

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

    /**
     * Creates an organization using a {@link OrganizationTO} object.
     *
     * @param organization Organization that is requested to be generated in {@link OrganizationTO} form.
     * @throws InsufficientInformationException
     *          If there is not enough information to build an organization object.
     */
    void createOrganization(OrganizationTO organization) throws InsufficientInformationException;

    /**
     * Obtains all of the organizations for a given user.
     *
     * @param user User that we want to get the organization information about.
     * @return {@link List} of {@link OrganizationTO} objects that correspond to a given user.
     */
    OrganizationTO getUserOrganizations(SecureRestEasyUser user);
}
