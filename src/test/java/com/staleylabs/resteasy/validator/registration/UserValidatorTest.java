package com.staleylabs.resteasy.validator.registration;

import com.staleylabs.resteasy.dao.UserDao;
import com.staleylabs.resteasy.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Unit Tests for the UserValidator class.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/21/13)
 */

public class UserValidatorTest {

    private static final String CORRECT_EMAIL = "sean@staleylabs.com";

    private static final String CORRECT_PASSWORD = "Pa$5word";

    private static final String INCORRECT_EMAIL = "spambot.23ti#@ijafdah";

    private static final String INCORRECT_PASSWORD = "password";

    private static final String USERNAME_AVAILABLE = "not-taken";

    private static final String USERNAME_UNAVAILABLE = "taken-already";

    @InjectMocks
    private UserValidator userValidator;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userValidator = new UserValidator();

        initMocks(this);
        when(userDao.getUserByUsername(USERNAME_AVAILABLE)).thenReturn(null);
        when(userDao.getUserByUsername(USERNAME_UNAVAILABLE)).thenReturn(new User());
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

    @Test
    public void testUsernameExists_true_when_unavailable() throws Exception {
        assertTrue(userValidator.usernameExists(USERNAME_UNAVAILABLE));
    }

    @Test
    public void testUsernameExists_true_when_available() throws Exception {
        assertFalse(userValidator.usernameExists(USERNAME_AVAILABLE));
    }

    @Test
    public void testValidatePasswordSyntax_works() throws Exception {
        assertTrue(userValidator.validatePasswordSyntax(CORRECT_PASSWORD));
    }

    @Test
    public void testValidatePasswordSyntax_false_with_bad_password() throws Exception {
        assertFalse(userValidator.validatePasswordSyntax(INCORRECT_PASSWORD));
    }
}
