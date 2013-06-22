package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.dao.UserDao;
import com.staleylabs.resteasy.domain.user.User;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.mapping.UserMapper;
import com.staleylabs.resteasy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserTO getUserByID(String id) {

        UserTO userTO = null;
        User user = userDao.getCertainUserById(id);

        if (user != null) {
            userTO = userMapper.transformUser(user);
        }
        return userTO;
    }

    @Override
    public List<UserTO> getAllUsers() {

        List<User> users = userDao.getAllUsers();

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
            userTO = userMapper.transformUser(userDao.getCertainUserById(userID));
        } else if (username != null) {
            userTO = userMapper.transformUser(userDao.getUserByUsername(username));
        } else {
            log.warn("Tried to find user. Failed.");
        }

        return userTO;
    }

    @Override
    public UserTO createUser(User user) throws InsufficientInformationException {
        long creationDate = new GregorianCalendar().getTimeInMillis();

        user.setCreationDate(creationDate);
        user.setEnabled(true);

        userDao.createUser(user);

        return userMapper.transformUser(userDao.getUserByUsername(user.getUsername()));
    }
}
