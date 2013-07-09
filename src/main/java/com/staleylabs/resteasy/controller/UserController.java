package com.staleylabs.resteasy.controller;

import com.staleylabs.resteasy.commons.RestEasyCommons;
import com.staleylabs.resteasy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String createUser(){
        return "user/createUser";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String visitUserProfile(@PathVariable String username, Model model) {
        log.debug("Accessing the user's profile.");

        model.addAttribute("user", userService.getUserTO(null, username));

        String usernamePageTitle = String.format("%s Profile :: RestEasy", username);
        model.addAttribute(RestEasyCommons.PAGE_TITLE_ATTRIBUTE, usernamePageTitle);

        return "user/userProfile";
    }
}
