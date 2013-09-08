package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * StaleyLabs
 *
 * @author Sean M. Staley
 * @version 1.0 (9/6/13)
 */

public class UserServiceProxyTest {

    private static final String USER_ID = "abc123";

    private static final String USERNAME = "admin";

    private static final String PASSWORD = "abc123abc";

    private static final String ORGANIZATION_ID = "321cba";

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @InjectMocks
    private UserServiceProxy userServiceProxy;

    @Mock
    private UserServiceImpl userService;

    private SecureRestEasyUser admin;

    private SecureRestEasyUser notAdmin;

    @Before
    public void setUp() throws Exception {
        userServiceProxy = new UserServiceProxy();
        admin = (SecureRestEasyUser) buildSecureUser(true);
        notAdmin = (SecureRestEasyUser) buildSecureUser(false);

        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        admin = null;
        notAdmin = null;
    }

    @Test
    public void testGetUserByID() throws Exception {
        userServiceProxy.getUserByID(USER_ID);

        verify(userService, times(1)).getUserByID(USER_ID);
    }

    @Test
    public void testGetUserTO() throws Exception {
        userServiceProxy.getUserTO(USER_ID, USERNAME);

        verify(userService, times(1)).getUserTO(USER_ID, USERNAME);
    }

    /**
     * Builds out a nice mock user for testing credentials with
     *
     * @return {@link SecureRestEasyUser} that will be used for testing.
     */
    private Object buildSecureUser(boolean isAdmin) {
        SecureRestEasyUser user;

        if (isAdmin) {
            final List<GrantedAuthority> authorities = new ArrayList<>(2);
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            user = new SecureRestEasyUser(USER_ID, USERNAME, PASSWORD, true, true, true, true, authorities, ORGANIZATION_ID);
        } else {
            final List<GrantedAuthority> authorities = new ArrayList<>(1);
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            user = new SecureRestEasyUser(USER_ID, USERNAME, PASSWORD, true, true, true, true, authorities, ORGANIZATION_ID);
        }

        return user;
    }
}
