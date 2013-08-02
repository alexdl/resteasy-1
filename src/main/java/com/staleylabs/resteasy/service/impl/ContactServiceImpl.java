package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.domain.ContactInformation;
import com.staleylabs.resteasy.domain.user.RegisteringUser;
import com.staleylabs.resteasy.service.ContactService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link ContactService} interface.
 *
 * @author Sean M. Staley
 * @version 1.0 (7/30/13)
 */

@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger log = Logger.getLogger(ContactServiceImpl.class.getName());

    private static final String SPECIAL_CHARACTER_REGEX = "[^0-9]";

    @Override
    public ContactInformation generatePersonalObjectFromRegisteringUser(RegisteringUser user) {
        log.debug("Generating personal contact information object.");

        ContactInformation personalInformation = new ContactInformation();

        personalInformation.setAddressLine1(user.getAddressLine1());
        personalInformation.setAddressLine2(user.getAddressLine2());
        personalInformation.setCityName(user.getCityName());
        personalInformation.setProvidenceCode(user.getStateCode());
        personalInformation.setPostalCode(Integer.parseInt(user.getPostalCode()));

        personalInformation.setPersonalNumber(trimPhoneNumber(user.getPersonalPhoneNumber()));

        return personalInformation;
    }

    @Override
    public ContactInformation generateOrganizationObjectFromRegisteringUser(RegisteringUser user) {
        log.debug("Generating organization's contact information object.");

        ContactInformation organizationInformation = new ContactInformation();

        organizationInformation.setAddressLine1(user.getOrganizationAddressLine1());
        organizationInformation.setAddressLine2(user.getOrganizationAddressLine2());
        organizationInformation.setCityName(user.getOrganizationCityName());
        organizationInformation.setProvidenceCode(user.getOrganizationStateCode());
        organizationInformation.setPostalCode(Integer.parseInt(user.getOrganizationPostalCode()));

        organizationInformation.setOfficeNumber(trimPhoneNumber(user.getOrganizationPhoneNumber()));

        return organizationInformation;
    }

    @Override
    public long trimPhoneNumber(String phoneNumber) {
        log.debug("Parsing phone number into a series of numbers. Before: " + phoneNumber);

        phoneNumber = phoneNumber.replaceAll(SPECIAL_CHARACTER_REGEX, "");

        log.debug("After parsing: " + phoneNumber);

        return Long.parseLong(phoneNumber);
    }
}
