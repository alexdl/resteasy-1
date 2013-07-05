package com.staleylabs.resteasy.controller.auth;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Sean M. Staley
 * @version X.X (7/4/13)
 */

@Controller
public class LogoutController {

    private static final Logger log = Logger.getLogger(LogoutController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logoff() {
        return "redirect:/login";
    }
}
