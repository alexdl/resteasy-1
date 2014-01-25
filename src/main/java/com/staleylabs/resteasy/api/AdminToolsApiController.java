package com.staleylabs.resteasy.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/mail/test", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void testMailConnection(HttpServletResponse response) throws IOException {
    }

    @RequestMapping(value = "/mail/debug/{debugMode}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void testMailConnection(@PathVariable boolean debugMode) throws IOException {
    }
}
