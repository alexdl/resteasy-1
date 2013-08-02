package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.domain.ContactInformation;
import com.staleylabs.resteasy.domain.user.RegisteringUser;
import com.staleylabs.resteasy.service.ContactService;
import com.staleylabs.resteasy.service.impl.ContactServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Proxy class for the {@link ContactService} class.
 *
 * @author Sean M. Staley
 * @version 1.0 (7/30/13)
 */

@Service(value = "contactService")
public class ContactServiceProxy implements ContactService {

    private static final Logger log = Logger.getLogger(ContactServiceProxy.class.getName());

    @Autowired
    private ContactServiceImpl contactService;

    @Override
    public ContactInformation generatePersonalObjectFromRegisteringUser(RegisteringUser user) {
        return contactService.generatePersonalObjectFromRegisteringUser(user);
    }

    @Override
    public ContactInformation generateOrganizationObjectFromRegisteringUser(RegisteringUser user) {
        return contactService.generateOrganizationObjectFromRegisteringUser(user);
    }

    @Override
    public long trimPhoneNumber(String phoneNumber) {
        log.debug("Asserting the incoming number is not null.");

        if (phoneNumber != null) {
            log.debug("Phone number is not null.");
            return contactService.trimPhoneNumber(phoneNumber);
        }
        log.debug("Phone number was null. Returning 0");

        return 0;
    }
}
