package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.beans.forms.RegisteringUser;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserService userServiceImpl;

    @Override
    public UserTO getUserByID(String id) {
        return userServiceImpl.getUserByID(id);
    }

    @Override
    public List<UserTO> getAllUsers() throws InsufficientPrivilegeException {
        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return userServiceImpl.getAllUsers();
            }
        }
        throw new InsufficientPrivilegeException();
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

    @Override
    public void deleteUserById(String userId) throws InsufficientPrivilegeException {
        boolean privileged = false;
        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                userServiceImpl.deleteUserById(userId);

                privileged = true;
                break;
            }
        }

        if (user.getUserId().equals(userId)) {
            userServiceImpl.deleteUserById(userId);
            privileged = true;
        }

        if (!privileged) {
            throw new InsufficientPrivilegeException();
        }
    }

    @Override
    public UserTO updateUserOrganizations(String userId, String organizationID) {
        return userServiceImpl.updateUserOrganizations(userId, organizationID);
    }
}
