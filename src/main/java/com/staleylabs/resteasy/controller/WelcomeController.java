package com.staleylabs.resteasy.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the first page that the end user views when logging into the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (7/4/13)
 */

@Controller
public class WelcomeController {

    private static final Logger log = Logger.getLogger(WelcomeController.class.getName());

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String getWelcomePage() {
        log.debug("User is entering the application.");

        return "welcome/welcome";
    }
}
