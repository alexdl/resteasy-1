package com.staleylabs.resteasy.controller.auth;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Logging out of the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (7/4/13)
 */

@Controller
public class LogoutController {

    private static final Logger log = Logger.getLogger(LogoutController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logoff(HttpSession session) {
        session.invalidate();

        return "redirect:/login";
    }
}
