package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.domain.user.RegisteringUser;
import com.staleylabs.resteasy.service.OrganizationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Sean M. Staley
 * @version X.X (8/1/13)
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
}
