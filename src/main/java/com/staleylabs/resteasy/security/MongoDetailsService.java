package com.staleylabs.resteasy.security;

import com.staleylabs.resteasy.dao.UserDao;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Extension of the {@link UserDetailsService} interface so that Spring Security will play well with the Mongo data
 * source that the application will be using.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/15/13)
 */

@Configuration
public class MongoDetailsService implements UserDetailsService {

    private static final Logger LOG = Logger.getLogger(MongoDetailsService.class.getName());

    private static final String ADMIN_USERNAME = "admin";

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    /**
     * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
     *
     * @param roles {@link String} of roles
     * @return list of granted authorities
     */
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        username = StringUtils.lowerCase(username);
        com.staleylabs.resteasy.domain.user.User user = userDao.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username + " not found in RestEasy.");
        }

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = user.isEnabled();
        int userRole = user.getRole();

        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities(userRole));
    }

    /**
     * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
     *
     * @param role the numerical role
     * @return a collection of {@link GrantedAuthority
     */
    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        return getGrantedAuthorities(getRoles(role));
    }

    /**
     * Converts a numerical role to an equivalent list of roles
     *
     * @param role the numerical role
     * @return list of roles as as a list of {@link String}
     */
    public List<String> getRoles(Integer role) {
        List<String> roles = new ArrayList<>();

        if (role == 1) {
            roles.add("ROLE_USER");
            roles.add("ROLE_ADMIN");

        } else if (role == 2) {
            roles.add("ROLE_USER");
        }

        return roles;
    }

    /**
     * After the {@link MongoDetailsService} bean has been created, we have enough wired up beans to setup the default
     * super user in the application.
     * <p/>
     * TODO: Move this into it's own bean that will setup multiple system properties and setup the admin user.
     */
    @PostConstruct
    private void setupAdminUser() {

        LOG.info("Setting up admin user. Checking to see if user exists.");

        if (userDao.getUserByUsername(ADMIN_USERNAME) == null) {
            LOG.info("ADMIN did not exist, creating them now with username " + ADMIN_USERNAME);

            com.staleylabs.resteasy.domain.user.User adminUser = new com.staleylabs.resteasy.domain.user.User();

            adminUser.setUsername(ADMIN_USERNAME);

            String password = passwordEncoder.encode(ADMIN_USERNAME);
            adminUser.setPassword(password);

            adminUser.setEmailAddress("admin@staleylabs.com");
            adminUser.setFirstName(ADMIN_USERNAME);
            adminUser.setLastName("Administrator");

            adminUser.setEnabled(true);
            adminUser.setCreationDate(1372918900);
            adminUser.setLastLoggedIn(1372918900);
            adminUser.setRole(2);

            userDao.save(adminUser);
        }
    }
}
