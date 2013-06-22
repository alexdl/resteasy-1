package com.staleylabs.resteasy.validator.registration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit Tests for the UserValidator class.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/21/13)
 */

public class UserValidatorTest {

    private static final String CORRECT_EMAIL = "sean@staleylabs.com";

    private static final String INCORRECT_EMAIL = "spambot.23ti#@ijafdah";

    private UserValidator userValidator;

    @Before
    public void setUp() throws Exception {
        userValidator = new UserValidator();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testValidateEmailString_works_with_correct_syntax() {
        assertTrue(userValidator.validateEmailString(CORRECT_EMAIL));
    }

    @Test
    public void testValidateEmailString_works_not_with_incorrect_syntax() {
        assertFalse(userValidator.validateEmailString(INCORRECT_EMAIL));
    }
}
