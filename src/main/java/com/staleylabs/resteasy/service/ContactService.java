package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.domain.ContactInformation;
import com.staleylabs.resteasy.domain.user.RegisteringUser;

/**
 * Service used to perform anything to the contact information of a given entity. This would include getting, setting,
 * or configuring anything to do with a collection of contacting information.
 *
 * @author Sean M. Staley
 * @version 1.0 (7/30/13)
 */

public interface ContactService {

    /**
     * Generates a new {@link ContactInformation} object based on the information provided the newly registered user.
     *
     * @param user User object that is being registered for the first time.
     * @return An object containing all personal contact information about an incoming user.
     */
    ContactInformation generatePersonalObjectFromRegisteringUser(RegisteringUser user);

    /**
     * Generates a new {@link ContactInformation} object based on the information provided the newly registered user.
     *
     * @param user User object that is being registered for the first time.
     * @return An object containing all personal contact information about an incoming user.
     */
    ContactInformation generateOrganizationObjectFromRegisteringUser(RegisteringUser user);

    /**
     * Utility method used to trim any special character and spaces from a phone number inputed into the application.
     *
     * @param phoneNumber {@link String} representation of how the phone number looks to the user.
     * @return Digit-only phone number without and spaces or special characters.
     */
    long trimPhoneNumber(String phoneNumber);
}
