package com.staleylabs.resteasy.dao.impl;

import com.staleylabs.resteasy.dao.UserDao;
import com.staleylabs.resteasy.domain.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the UserDao interface.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/15/13)
 * @see UserDao
 */

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class.getName());

    @Autowired
    private MongoOperations mongoTemplate;

    @Override
    public User getCertainUserById(String id) {
        assert (id != null) : id = "0";

        Query query = new Query(Criteria.where("id").is(id));

        log.info("Query generated is: " + query.toString());

        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User getUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));

        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public void createUser(User user) {
        log.debug("Creating a new user.");

        mongoTemplate.save(user);
    }
}
