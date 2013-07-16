package com.staleylabs.resteasy.controller;

import com.staleylabs.resteasy.commons.RestEasyCommons;
import com.staleylabs.resteasy.domain.user.User;
import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.service.UserService;
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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createUser() {
        return new ModelAndView("user/createUser", "user-entity", new User());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String userCreationForm(@ModelAttribute User user) {
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
    @RequestMapping(value = "/taken/{username}", method = RequestMethod.GET)
    @ResponseBody
    public boolean visitUserProfile(@PathVariable String username) {
        log.debug("Checking to see if user " + username + " has been taken.");

        return (userService.getUserTO(null, username) != null);
    }
}
