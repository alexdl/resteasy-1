package com.staleylabs.resteasy.controller;

import com.staleylabs.resteasy.dto.UserTO;
import com.staleylabs.resteasy.globals.RestEasyGlobals;
import com.staleylabs.resteasy.service.SystemService;
import com.staleylabs.resteasy.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.staleylabs.resteasy.commons.RestEasyCommons.PAGE_TITLE_ATTRIBUTE;

/**
 * Admin Page
 *
 * @author Sean M. Staley
 * @version 1.0 (8/4/13)
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private static final Logger log = Logger.getLogger(AdminController.class.getName());

    private static final String SYSTEM_INFORMATION = "systemInformation";

    private static final String SYSTEM_PROPERTIES = "systemProperties";

    private static final String UPTIME_INFORMATION = "uptimeInformation";

    @Autowired
    protected SystemService systemService;

    @Autowired
    protected RestEasyGlobals restEasyGlobals;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.GET)
    public String visitAdminConsole(Model model) {
        log.info("Accessing Admin Console");

        model.addAttribute(PAGE_TITLE_ATTRIBUTE, "Admin Console :: RestEasy");
        model.addAttribute(SYSTEM_INFORMATION, systemService.getSystemInformation());
        model.addAttribute(UPTIME_INFORMATION, systemService.getUpTimeInformation());
        model.addAttribute(SYSTEM_PROPERTIES, systemService.getSystemProperties());

        return "admin/admin";
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public String visitUserPage(@PathVariable String username, Model model) {
        log.info("Accessing Admin Console");

        UserTO user = userService.getUserTO(null, username);

        model.addAttribute("user", user);
        model.addAttribute("lastLoggedIn", new Date(user.getLastLoggedIn()));
        model.addAttribute("creationDate", new Date(user.getCreationDate()));
        model.addAttribute(PAGE_TITLE_ATTRIBUTE, "Admin Console :: RestEasy");

        return "admin/userProfile";
    }

    /**
     * Sets a new system property in the application.
     *
     * @param code {@link String} representation of a code provided by an end user.
     * @return <code>true</code> if everything goes well.
     */
    @RequestMapping(value = "/applySystemProperty", method = RequestMethod.GET)
    @ResponseBody
    public boolean promotionalCheck(@RequestBody String propertyKey, @RequestBody String propertyValue) {
        restEasyGlobals.setProperty(propertyKey, propertyValue);
        return true;
    }
}
