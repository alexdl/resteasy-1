package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.beans.forms.RegisteringUser;
import com.staleylabs.resteasy.beans.mail.Letter;
import com.staleylabs.resteasy.dao.UserDao;
import com.staleylabs.resteasy.domain.User;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;
import com.staleylabs.resteasy.globals.RestEasyGlobals;
import com.staleylabs.resteasy.mail.MailEngine;
import com.staleylabs.resteasy.mapping.UserMapper;
import com.staleylabs.resteasy.service.ContactService;
import com.staleylabs.resteasy.service.OrganizationService;
import com.staleylabs.resteasy.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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

    @Inject
    private BCryptPasswordEncoder passwordEncoder;

    @Inject
    private ContactService contactService;

    @Inject
    private MongoOperations mongoOperations;

    @Inject
    private OrganizationService organizationService;

    @Inject
    private RestEasyGlobals restEasyGlobals;

    @Inject
    private UserDao userDao;

    @Inject
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
    public UserTO createUser(RegisteringUser user) throws InsufficientInformationException {
        log.info("Creating new user in the application.");

        User userObject = new User();

        String username = StringUtils.lowerCase(user.getUsername());
        userObject.setUsername(username);

        String password = generatePasswordHash(user.getPassword());
        userObject.setPassword(password);

        String email = user.getEmail();
        userObject.setEmailAddress(email);

        userObject.setFirstName(user.getFirstName());
        userObject.setMiddleName(user.getMiddleName());
        userObject.setLastName(user.getLastName());

        long creationDate = new GregorianCalendar().getTimeInMillis();
        userObject.setCreationDate(creationDate);
        userObject.setLastLoggedIn(creationDate);

        userObject.setEnabled(true);

        int role = (userObject.getRole() < 0 || userObject.getRole() > 2) ? 1 : userObject.getRole();
        userObject.setRole(role);

        userObject.setContact(contactService.generatePersonalObjectFromRegisteringUser(user));

        String organizationID = organizationService.getIdFromOrganizationName(user.getOrganizationName());

        if (organizationID == null && StringUtils.isNotBlank(user.getOrganizationName())) {
            userObject.setOrganizationId(organizationService.generateOrganizationFromRegisteringUser(user).getId());
        } else {
            userObject.setOrganizationId(organizationID);
        }

        userDao.save(userObject);

        log.info("Finished creating new user " + username);

        final Letter letter = new Letter();
        letter.setReceiverName(userObject.getFirstName() + " " + userObject.getLastName());
        letter.setReceiverEmail(userObject.getEmailAddress());
        letter.setSenderEmail("no-reply@staleylabs.com");
        letter.setSenderName("RestEasy Administrator");
        letter.setSubject("Welcome to RestEasy!");
        letter.setHtmlBody("<i>Welcome to RestEasy!</i><br>Click here to go to the <a href=\"resteasy.herokuapp.com\">site</a>.");
        letter.setTextBody("Welcome to RestEasy!");

        log.info("Sending welcome email was a " + MailEngine.sendMessage(letter));

        return userMapper.transformUser(userDao.getUserByUsername(user.getUsername()));
    }

    @Override
    public List<UserTO> getSubsetAllUsers(int pageNumber) {
        int PAGE_SIZE = restEasyGlobals.getIntegerPropertyWithDefault("resteasy.admin.user.pagination", 10);
        List<UserTO> userTOs = new ArrayList<>();

        Pageable pageable = new PageRequest(pageNumber - 1, PAGE_SIZE);

        List<User> users = userDao.findAll(pageable).getContent();

        for (User user : users) {
            UserTO userTO = userMapper.transformUser(user);
            userTOs.add(userTO);
        }

        return userTOs;
    }

    @Override
    public void deleteUserById(String userId) throws InsufficientPrivilegeException {
        log.debug("Removing user from the data source now.");

        userDao.delete(userId);

        log.debug("User is no longer in the application data source.");
    }

    @Override
    public UserTO updateUserOrganizations(String userId, String organizationID) {

        Update update = new Update();

        if (StringUtils.isBlank(organizationID)) {
            update.unset("organizationId");
        } else {
            update.set("organizationId", organizationID);
        }

        mongoOperations.updateFirst(new Query().addCriteria(Criteria.where("id").is(userId)), update, User.class);

        return userMapper.transformUser(userDao.findOne(userId));
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
