package com.staleylabs.resteasy.validator.registration;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Class that can be utilized to validate various parts of the registration process.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/21/13)
 */

@Component
public class UserValidator {

    private static final Logger log = Logger.getLogger(UserValidator.class.getName());

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validates a given email address to verify that it is in the form of a correct syntax.
     *
     * @param emailAddress Email address the application would like to check.
     * @return <code>true</code> if the email address does match a correct email syntax. <code>false</code> otherwise.
     */
    public boolean validateEmailString(String emailAddress) {
        return Pattern.matches(EMAIL_REGEX, emailAddress);
    }
}
