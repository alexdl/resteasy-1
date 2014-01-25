package com.staleylabs.resteasy.api;

import com.staleylabs.resteasy.beans.EmailMessage;
import com.staleylabs.resteasy.mail.Mailer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * API service methods that can be used by an administrator.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/8/13)
 */

@RestController
@RequestMapping(value = "/api/admin")
public class AdminToolsApiController {

    @Inject
    private Mailer mailman;

    @RequestMapping(value = "/mail/test", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void testMailConnection(@RequestBody EmailMessage message, HttpServletResponse response) throws IOException {
        message.setSubject("RestEasy Test Email");
        message.setMessageBody("This is a test email from Mr. Admin.");
        message.setSender("admin@staleylabs.com");

        try {
            mailman.sendMessage(message);
        } catch (MessagingException e) {
            response.sendError(500, "There was an error when trying to send message.");
        }
    }

    @RequestMapping(value = "/mail/debug/{debugMode}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void testMailConnection(@PathVariable boolean debugMode) throws IOException {
        mailman.setDebugMode(debugMode);
    }
}
