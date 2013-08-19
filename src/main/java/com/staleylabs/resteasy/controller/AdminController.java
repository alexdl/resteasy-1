package com.staleylabs.resteasy.controller;

import com.staleylabs.resteasy.commons.RestEasyCommons;
import com.staleylabs.resteasy.globals.RestEasyGlobals;
import com.staleylabs.resteasy.service.SystemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(method = RequestMethod.GET)
    public String visitAdminConsole(Model model) {
        log.info("Accessing Admin Console");

        model.addAttribute(RestEasyCommons.PAGE_TITLE_ATTRIBUTE, "Admin Console :: RestEasy");
        model.addAttribute(SYSTEM_INFORMATION, systemService.getSystemInformation());
        model.addAttribute(UPTIME_INFORMATION, systemService.getUpTimeInformation());
        model.addAttribute(SYSTEM_PROPERTIES, systemService.getSystemProperties());

        return "admin/admin";
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
