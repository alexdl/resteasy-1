package com.staleylabs.resteasy.api;

import com.staleylabs.resteasy.dao.OrganizationDao;
import com.staleylabs.resteasy.dto.HotelTO;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.HotelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * API methods for all things Hotel related.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/25/13)
 */

@Controller
@RequestMapping(value = "/api/hotel")
public class HotelApiController {

    private static final Logger log = Logger.getLogger(HotelApiController.class.getName());

    @Autowired
    private HotelService hotelService;

    @Autowired
    private OrganizationDao organizationDao;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Collection<HotelTO> getAllHotels() {
        log.debug("Requesting all hotels in the application.");

        return hotelService.getAllHotels();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Collection<HotelTO> getUserHotels() {
        log.debug("User requesting their hotels.");

        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return hotelService.getUserHotels(user.getOrganizationID());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void createHotel(@RequestBody HotelTO hotelTO) {
        log.debug("User requesting their hotels.");

        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        hotelService.addHotelToOrganization(hotelTO, organizationDao.findOne(user.getOrganizationID()));
    }
}
