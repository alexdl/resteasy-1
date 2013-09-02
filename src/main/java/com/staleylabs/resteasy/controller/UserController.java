package com.staleylabs.resteasy.controller;

import com.staleylabs.resteasy.beans.forms.RegisteringUser;
import com.staleylabs.resteasy.commons.RestEasyCommons;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.service.UserService;
import com.staleylabs.resteasy.validator.registration.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for all things related to a user in the UI. This includes creating a user, updating a user, visiting a
 * user's profile page, etc.
 *
 * @author Sean M. Staley
 * @version 1.0 (7/6/13)
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserValidator userValidator;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createUser() {
        return new ModelAndView("user/createUser", "registering-user-entity", new RegisteringUser());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String userCreationForm(@ModelAttribute RegisteringUser user) {
        log.debug("Creating user from UI with username of " + user.getUsername());

        UserTO userTO = null;

        try {
            userTO = userService.createUser(user);
        } catch (InsufficientInformationException e) {
            log.error("UI allowed a user lacking information into the action.", e);
        }

        if (userTO != null) {
            return "redirect:user/createUser";
        }

        return "login";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String visitUserProfile(@PathVariable String username, Model model) {
        log.debug("Accessing the user's profile.");

        model.addAttribute("user", userService.getUserTO(null, username));

        String usernamePageTitle = String.format("%s Profile :: RestEasy", username);
        model.addAttribute(RestEasyCommons.PAGE_TITLE_ATTRIBUTE, usernamePageTitle);

        return "user/userProfile";
    }

    /**
     * Application side check to see if this user has been used. This will be used during the registration process.
     *
     * @param username The username that the user would like to have for their account.
     * @return <code>true</code> if the user has been taken, <code>false otherwise</code>.
     */
    @RequestMapping(value = "/create/taken/{username}", method = RequestMethod.GET)
    @ResponseBody
    public boolean visitUserProfile(@PathVariable String username) {
        log.debug("Checking to see if user " + username + " has been taken.");

        return (userService.getUserTO(null, username) != null);
    }

    /**
     * Call that is used to check promotional codes for the application.
     *
     * @param code {@link String} representation of a code provided by an end user.
     * @return <code>true</code> if the code is valid, <code>false</code> otherwise.
     */
    @RequestMapping(value = "/create/promo/{code}", method = RequestMethod.GET)
    @ResponseBody
    public boolean promotionalCheck(@PathVariable String code) {
        return (code.equals("magnolia3323"));
    }

    /**
     * Call that can be used to verify a given incoming user's password.
     *
     * @param password {@link String} representation of a requested password.
     * @return <code>true</code> if the password is valid, <code>false</code> otherwise.
     */
    @RequestMapping(value = "/create/password", method = RequestMethod.POST)
    @ResponseBody
    public boolean passwordCheck(@RequestParam String password) {
        return userValidator.validatePasswordSyntax(password);
    }
}
