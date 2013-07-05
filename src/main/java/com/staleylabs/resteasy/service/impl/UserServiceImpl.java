package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.dao.UserDao;
import com.staleylabs.resteasy.domain.user.User;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.mapping.UserMapper;
import com.staleylabs.resteasy.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementation of the UserService service interface.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/15/13)
 * @see UserService
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserTO getUserByID(String id) {

        UserTO userTO = null;
        User user = userDao.findOne(id);

        if (user != null) {
            userTO = userMapper.transformUser(user);
        }
        return userTO;
    }

    @Override
    public List<UserTO> getAllUsers() {

        List<User> users = userDao.findAll();

        List<UserTO> userTOs = new ArrayList<>();

        for (User user : users) {
            UserTO userTO = userMapper.transformUser(user);
            userTOs.add(userTO);
        }

        return userTOs;
    }

    @Override
    public UserTO getUserTO(String userID, String username) {
        UserTO userTO = null;

        if (userID != null) {
            userTO = userMapper.transformUser(userDao.getUserById(userID));
        } else if (username != null) {
            userTO = userMapper.transformUser(userDao.getUserByUsername(username));
        } else {
            log.warn("Tried to find user. Failed.");
        }

        return userTO;
    }

    @Override
    public UserTO createUser(User user) throws InsufficientInformationException {
        String username = StringUtils.lowerCase(user.getUsername());
        user.setUsername(username);

        String password = generatePasswordHash(user.getPassword());
        user.setPassword(password);

        long creationDate = new GregorianCalendar().getTimeInMillis();
        user.setCreationDate(creationDate);

        long lastLoggedInDate = new GregorianCalendar().getTimeInMillis();
        user.setLastLoggedIn(lastLoggedInDate);

        user.setEnabled(true);

        int role = (user.getRole() < 0 || user.getRole() > 2) ? 1 : user.getRole();
        user.setRole(role);

        userDao.save(user);

        return userMapper.transformUser(userDao.getUserByUsername(user.getUsername()));
    }

    /**
     * Method to generate a hash for the password of the user.
     *
     * @param password <b>Raw</b> password for the incoming user.
     * @return {@link String} representing the hashed password for the incoming user.
     */
    private String generatePasswordHash(String password) {
        String hashedPassword = null;

        if (password == null) {
            log.debug("User just logged an empty password!");
            return hashedPassword;
        }

        hashedPassword = passwordEncoder.encode(password);

        log.debug("Encoded password for user to " + hashedPassword);

        return hashedPassword;
    }
}
