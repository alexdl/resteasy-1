package com.staleylabs.resteasy.dao;

import com.staleylabs.resteasy.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object Interface that can be used to obtain {@link User} objects from a given source of data.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/15/13)
 */

@Repository
public interface UserDao extends MongoRepository<User, String>{

    /**
     * Method that can be used to get a user from the application's data source by the User ID provided to the end
     * user.
     *
     * @param id {@link String} representation of the user's ID.
     * @return {@link User} object representing the user directly from the data source.
     */
    User getUserById(String id);

    /**
     * Accesses the data source and obtains the user based on the username value passed in.
     *
     * @param username {@link String} representation of the username of the user requested.
     * @return {@link User} object representing the end user.
     */
    User getUserByUsername(String username);
}
