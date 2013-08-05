package com.staleylabs.resteasy.validator.registration;

import com.staleylabs.resteasy.dao.UserDao;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*" +
            "(\\.[A-Za-z]{2,})$";

    @Autowired
    private UserDao userDao;

    /**
     * Validates a given email address to verify that it is in the form of a correct syntax.
     *
     * @param emailAddress Email address the application would like to check.
     * @return <code>true</code> if the email address does match a correct email syntax. <code>false</code> otherwise.
     */
    public boolean validateEmailString(String emailAddress) {
        log.debug("Matching email string against custom regex values.");

        return Pattern.matches(EMAIL_REGEX, emailAddress);
    }

    /**
     * Validation that the username requested by the incoming user has not already been taken by another user in the
     * system. Username <b>will be morphed to lowercase syntax</b> in order to perform comparison.
     *
     * @param username Username that has requested to be taken in by the user.
     * @return <code>true</code> if the username can be used. <code>false</code> otherwise.
     */
    public boolean usernameExists(String username) {
        String requested = StringUtils.lowerCase(username);

        log.debug("Incoming username for check is: " + requested);

        return userDao.getUserByUsername(requested) != null;
    }

    /**
     * Validates that the password fits the criteria specified by the application. The syntax that is needed by the
     * application is as follows:
     * <ol>
     *     <li>Must contain 6 characters</li>
     *     <li>Must contain 1 uppercase letter</li>
     *     <li>Must contain 1 lowercase letter</li>
     *     <li>Must contain one special character</li>
     * </ol>
     *
     * @param password Password that is requested by the incoming user.
     * @return <code>true</code> if the password is good for the application, <code>false</code> otherwise.
     */
    public boolean validatePasswordSyntax(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}
