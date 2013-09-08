package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.beans.forms.RegisteringUser;
import com.staleylabs.resteasy.domain.User;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;

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
     * Gets all users found in the application's data source.
     *
     * @return {@link List} of {@link UserTO} objects.
     * @throws InsufficientPrivilegeException Occurs when a user is not an {@code ROLE_ADMIN} user in the application.
     */
    List<UserTO> getAllUsers() throws InsufficientPrivilegeException;

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

    /**
     * Obtains a list of users within a given range. This would be utilized for getting a page of user objects.
     *
     * @param pageNumber The current page that the end user is viewing in the admin console.
     * @return {@link List} of {@link UserTO} objects on the given {@code pageNumber}.
     */
    List<UserTO> getSubsetAllUsers(int pageNumber);

    /**
     * Deletes a given user from the application by a user ID instead of object.
     *
     * @param userId {@link String} representation of a user's ID that should be removed from the application.
     * @throws InsufficientPrivilegeException Occurs when the user is not an {@code ROLE_ADMIN} user or is not trying
     *                                        to remove their own account from the system.
     */
    void deleteUserById(String userId) throws InsufficientPrivilegeException;

    /**
     * Updates a given user based on {@code userID} with a list of new {@code organizationIDs}.
     *
     * @param userId         {@link String} representation of a user's ID.
     * @param organizationID {@link String} ID for the organization associated with a user. The ID can be {@code null}.
     * @return The new user object for a given user.
     */
    UserTO updateUserOrganizations(String userId, String organizationID);
}
