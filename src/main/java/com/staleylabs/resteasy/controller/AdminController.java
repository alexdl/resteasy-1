package com.staleylabs.resteasy.controller;

import com.staleylabs.resteasy.commons.RestEasyCommons;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(method = RequestMethod.GET)
    public String visitAdminConsole(Model model) {
        log.info("Accessing Admin Console");

        model.addAttribute(RestEasyCommons.PAGE_TITLE_ATTRIBUTE, "Admin Console :: RestEasy");

        return "admin/admin";
    }
}
