package com.staleylabs.resteasy.security;

import com.staleylabs.resteasy.dao.UserDao;
import com.staleylabs.resteasy.domain.user.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Extension of the {@link UserDetailsService} interface so that Spring Security will play well with the Mongo data
 * source that the application will be using.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/15/13)
 */

@Configuration
public class MongoDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(StringUtils.lowerCase(username));

        if (user == null) {
            return null;
        }

        Set<GrantedAuthorityImpl> authorities = new HashSet<>();
        authorities.add(new GrantedAuthorityImpl("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPasswordHash(),
                user.isEnabled(), true, true, true, authorities);
    }
}
