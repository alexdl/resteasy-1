package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.domain.user.RegisteringUser;
import com.staleylabs.resteasy.dto.OrganizationTO;
import com.staleylabs.resteasy.service.OrganizationService;
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
}
