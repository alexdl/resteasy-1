package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.beans.Room;
import com.staleylabs.resteasy.domain.Hotel;
import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.dto.HotelTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.security.SecureRestEasyUser;
import com.staleylabs.resteasy.service.HotelService;
import com.sun.servicetag.UnauthorizedAccessException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Proxy implementation for {@link HotelService}
 *
 * @author Sean M. Staley
 * @version 1.0 (8/25/13)
 */

@Service("hotelService")
public class HotelServiceProxy implements HotelService {

    private static final Logger LOGGER = Logger.getLogger(HotelServiceProxy.class.getName());

    @Autowired
    private HotelService hotelServiceImpl;

    @Override
    public void addRoomToHotel(Room room, String hotelID) throws InsufficientInformationException {
        if (room != null && (StringUtils.isNotBlank(room.getRoomName()) && room.getRoomCapacity() >= 1)) {
            hotelServiceImpl.addRoomToHotel(room, hotelID);
        } else {
            throw new InsufficientInformationException("Room object did not contain information needed.");
        }
    }

    @Override
    public void addHotelToOrganization(HotelTO hotelTO, Organization organization) throws UnauthorizedAccessException {
        LOGGER.trace("Checking permissions to add hotel to an organization.");
        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getOrganizationID().equals(organization.getId())) {
            hotelServiceImpl.addHotelToOrganization(hotelTO, organization);
        } else {
            throw new UnauthorizedAccessException("User is not allowed to add hotels to selected organization.");
        }
    }

    @Override
    public List<HotelTO> getAllHotels() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return hotelServiceImpl.getAllHotels();
            }
        }

        return null;
    }

    @Override
    public Collection<HotelTO> getUserHotels(String organizationID) {
        if (StringUtils.isNotBlank(organizationID)) {
            return hotelServiceImpl.getUserHotels(organizationID);
        } else {
            LOGGER.warn("Organization ID was blank. Not obtaining user hotels.");
            return null;
        }
    }

    @Override
    public void addHotelToOrganization(Hotel hotel, Organization organization) throws UnauthorizedAccessException {
        LOGGER.trace("Checking permissions to add hotel to an organization.");
        SecureRestEasyUser user = (SecureRestEasyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getOrganizationID().equals(organization.getId())) {
            hotelServiceImpl.addHotelToOrganization(hotel, organization);
        } else {
            throw new UnauthorizedAccessException("User is not allowed to add hotels to selected organization.");
        }
    }
}
