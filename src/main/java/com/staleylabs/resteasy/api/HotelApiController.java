package com.staleylabs.resteasy.api;

import com.staleylabs.resteasy.dao.OrganizationDao;
import com.staleylabs.resteasy.dto.HotelTO;
import com.staleylabs.resteasy.exception.InsufficientPrivilegeException;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.HotelService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

/**
 * API methods for all things Hotel related.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/25/13)
 */

@RestController
@RequestMapping(value = "/api/hotel")
public class HotelApiController {

    private static final Logger log = Logger.getLogger(HotelApiController.class.getName());

    @Inject
    private HotelService hotelService;

    @Inject
    private OrganizationDao organizationDao;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Collection<HotelTO> getAllHotels() {
        log.debug("Requesting all hotels in the application.");

        return hotelService.getAllHotels();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Collection<HotelTO> getUserHotels() {
        log.debug("User requesting their hotels.");

        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return hotelService.getUserHotels(user.getOrganizationID());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createHotel(@RequestBody HotelTO hotelTO) throws InsufficientPrivilegeException {
        log.debug("User requesting their hotels.");

        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        hotelService.addHotelToOrganization(hotelTO, organizationDao.findOne(user.getOrganizationID()));
    }
}
