package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.beans.forms.RegisteringUser;
import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.dto.OrganizationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.OrganizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Proxy class for the {@link OrganizationService} interface.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/1/13)
 */

@Service(value = "organizationService")
public class OrganizationServiceProxy implements OrganizationService {

    private static final Logger log = Logger.getLogger(OrganizationServiceProxy.class.getName());

    @Autowired
    private OrganizationService organizationServiceImpl;

    @Override
    public Organization generateOrganizationFromRegisteringUser(RegisteringUser user) {
        return organizationServiceImpl.generateOrganizationFromRegisteringUser(user);
    }

    @Override
    public String getIdFromOrganizationName(String organizationName) {
        return organizationServiceImpl.getIdFromOrganizationName(organizationName);
    }

    @Override
    public List<OrganizationTO> getAllOrganizations() {
        log.debug("Determining if the user is an admin user because they are requesting all organizations.");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return organizationServiceImpl.getAllOrganizations();
            }
        }

        return null;
    }

    @Override
    public void createOrganization(OrganizationTO organization) throws InsufficientInformationException {
        if (StringUtils.isNotBlank(organization.getOrganizationName()) && StringUtils.isNotBlank(organization.getAddressLine1())
                && StringUtils.isNotBlank(organization.getCity()) && StringUtils.isNotBlank(organization.getStateCode())) {
            organizationServiceImpl.createOrganization(organization);
        } else {
            throw new InsufficientInformationException("Not enough information to generate an organization.");
        }
    }

    @Override
    public OrganizationTO getUserOrganizations(SecureRestEasyUser user) {
        if (StringUtils.isNotBlank(user.getOrganizationID())) {
            return organizationServiceImpl.getUserOrganizations(user);
        }

        return null;
    }
}
