package com.staleylabs.resteasy.dao;

import com.staleylabs.resteasy.domain.user.User;

import java.util.List;

/**
 * Data Access Object Interface that can be used to obtain {@link User} objects from a given source of data.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/15/13)
 */

public interface UserDao {

    /**
     * Method that can be used to get a user from the application's data source by the User ID provided to the end
     * user.
     *
     * @param id {@link String} representation of the user's ID.
     * @return {@link User} object representing the user directly from the data source.
     */
    User getCertainUserById(String id);

    /**
     * Method that obtains all of the users in the data source into the application for consumption.
     *
     * @return {@link User} objects inside of a {@link List}.
     */
    List<User> getAllUsers();

    /**
     * Accesses the data source and obtains the user based on the username value passed in.
     *
     * @param username {@link String} representation of the username of the user requested.
     * @return {@link User} object representing the end user.
     */
    User getUserByUsername(String username);

    /**
     * Used to store {@link User} objects into the application database.
     *
     * @param user The object that is requested to persist into the application data source.
     */
    void createUser(User user);
}
