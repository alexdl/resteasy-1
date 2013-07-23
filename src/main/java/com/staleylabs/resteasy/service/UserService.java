package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.domain.user.RegisteringUser;
import com.staleylabs.resteasy.domain.user.User;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;

import java.util.List;

/**
 * Service method used to create, retrieve, update, or delete a given user in the application's data source. This is the
 * first step in modifying any user for any reason in the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/15/13)
 */

public interface UserService {

    /**
     * Used to get the UserTO corresponding to the ID provided by the calling method.
     *
     * @param id {@link String} representation of the User's ID.
     * @return {@link UserTO} object that represents the user found in the data source.
     */
    UserTO getUserByID(String id);

    /**
     * @return
     */
    List<UserTO> getAllUsers();

    /**
     * Obtains the UserTO object for the end user by one of the following ways:
     * <ul>
     * <li>UserID</li>
     * <li>Username</li>
     * </ul>
     * <p/>
     * Only one of the items are required in the method's parameters. If both are found, the application match a user by
     * the userID given. Otherwise, the method will match the user based on the field that has been given to it.
     *
     * @param userID   The {@link String} representation of the User's ID.
     * @param username The {@link String} representation of the User's username.
     * @return {@link UserTO} object that represents the user from the data source.
     */
    UserTO getUserTO(String userID, String username);

    /**
     * Service method that is used to create a user object and apply the creation time, enabled, password hash, etc.
     *
     * @param user {@link User} object.
     * @throws InsufficientInformationException
     *          Occurs when there is not enough information to build the user to the
     *          data source.
     */
    UserTO createUser(RegisteringUser user) throws InsufficientInformationException;
}
