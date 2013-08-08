package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.domain.user.RegisteringUser;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.service.UserService;
import com.staleylabs.resteasy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Proxy class that verifies that the user has the permissions to do the action requested on a set of objects.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/17/13)
 */

@Service(value = "userService")
public class UserServiceProxy implements UserService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public UserTO getUserByID(String id) {
        return userServiceImpl.getUserByID(id);
    }

    @Override
    public List<UserTO> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @Override
    public UserTO getUserTO(String userID, String username) {
        return userServiceImpl.getUserTO(userID, username);
    }

    @Override
    public UserTO createUser(RegisteringUser user) throws InsufficientInformationException {
        if ((user != null) && (user.getUsername() != null && user.getEmail() != null)
                && user.getFirstName() != null && user.getLastName() != null && user.getAddressLine1() != null
                && user.getCityName() != null && user.getStateCode() != null && user.getPostalCode() != null) {

            return userServiceImpl.createUser(user);
        } else {
            throw new InsufficientInformationException("User object did not contain all of the information needed.");
        }
    }

    @Override
    public List<UserTO> getSubsetAllUsers(int pageNumber) {
        return userServiceImpl.getSubsetAllUsers(pageNumber);
    }
}
