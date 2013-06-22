package com.staleylabs.resteasy.controller.auth;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A Spring MVC controller that is used to log into the application with.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/2/13)
 */

@Controller
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String getLoginView() {
        return "login";
    }
}
