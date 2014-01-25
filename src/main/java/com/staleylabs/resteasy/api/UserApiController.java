package com.staleylabs.resteasy.api;

import com.staleylabs.resteasy.beans.forms.RegisteringUser;
import com.staleylabs.resteasy.domain.User;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;
import com.staleylabs.resteasy.service.UserService;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * Spring MVC Controller that can be used to get information on a particular user from the application or to obtain
 * information on the user currently logged into the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/2/13)
 */

@Controller
@RequestMapping(value = "/api/user")
public class UserApiController {

    private static final Logger log = Logger.getLogger(UserApiController.class.getName());

    @Inject
    protected UserService userService;

    /**
     * API call that will get a specific user that has been selected by the application.
     *
     * @param userId {@link String} representation of the user's ID.
     * @return {@link UserTO} object that represents the user with the ID passed into the method.
     */
    @RequestMapping(value = "/id/{userId}", method = RequestMethod.GET)
    @ResponseStatus(OK)
    public UserTO getUserByUserId(@PathVariable String userId) {
        return userService.getUserByID(userId);
    }

    /**
     * API call that will get a specific user via username that has been selected by the application.
     *
     * @return {@link UserTO} object that represents the user with the ID passed into the method.
     */
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    @ResponseStatus(OK)
    public UserTO getUserByUsername(@PathVariable String username) {
        return userService.getUserTO(null, username);
    }

    /**
     * API call that is used to get a response of all users found in the application.
     *
     * @return JSON list of {@link UserTO} objects.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(OK)
    public List getAllUsers(HttpServletResponse response) throws IOException {
        log.debug("Requesting all users!");

        try {
            return userService.getAllUsers();
        } catch (InsufficientPrivilegeException e) {
            response.sendError(HttpStatus.SC_FORBIDDEN, Arrays.toString(e.getStackTrace()));
        }

        return Collections.EMPTY_LIST;
    }

    @RequestMapping(value = "/{pageNumber}", method = RequestMethod.GET)
    @ResponseStatus(OK)
    public List<UserTO> getAllUsersBySet(@PathVariable int pageNumber) {
        return userService.getSubsetAllUsers(pageNumber);
    }

    /**
     * API call that can be used to create a User via a JSON string. Once the user has created the user successfully,
     * the application will return a {@link UserTO} that represents the newly created user with a return code of
     * {@link HttpStatus#SC_OK}. The following parameters are required for this call to work correctly, they are also
     * case sensitive:
     * <ul>
     * <li><code>username</code></li>
     * <li><code>firstName</code></li>
     * <li><code>lastName</code></li>
     * <li><code>addressLine1</code></li>
     * <li><code>city</code></li>
     * <li><code>state</code></li>
     * <li><code>zip</code></li>
     * </ul>
     *
     * @param user     {@link User} that represented in the RequestBody as an <code>application/json</code> type.
     * @param response Parameter that will have the status set once the API call returns, with or without errors.
     * @return {@link UserTO} object that embodies the newly created user. Also comes with a status code of <code>200</code>
     * if the process did work correctly.
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(CREATED)
    public UserTO createUser(@RequestBody RegisteringUser user, HttpServletResponse response) throws IOException {
        UserTO userTO = null;

        try {
            userTO = userService.createUser(user);
        } catch (InsufficientInformationException e) {
            log.info(e);

            response.sendError(HttpStatus.SC_BAD_REQUEST, e.getMessage());
        }

        return userTO;
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(NO_CONTENT)
    public void removeUser(@PathVariable String userId, HttpServletResponse response) throws IOException {
        log.info("Removing user with ID " + userId + " from application...");

        if (StringUtils.isBlank(userId)) {
            response.sendError(HttpStatus.SC_BAD_REQUEST, "ID for user passed was empty.");
        } else {

            try {
                userService.deleteUserById(userId);
            } catch (InsufficientPrivilegeException e) {
                response.sendError(HttpStatus.SC_FORBIDDEN, e.getMessage());
            }
        }
    }
}
