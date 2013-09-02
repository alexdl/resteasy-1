package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.beans.Contact;
import com.staleylabs.resteasy.beans.Room;
import com.staleylabs.resteasy.dao.HotelDao;
import com.staleylabs.resteasy.domain.Hotel;
import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.dto.HotelTO;
import com.staleylabs.resteasy.mapping.HotelMapper;
import com.staleylabs.resteasy.service.HotelService;
import com.sun.servicetag.UnauthorizedAccessException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Actual implementation of the {@link HotelService} interface. Once the proxy has certified that the action can be
 * called, the {@code implementation} class will take over.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/25/13)
 */

@Service("hotelServiceImpl")
public class HotelServiceImpl implements HotelService {

    private static final Logger LOGGER = Logger.getLogger(HotelServiceImpl.class.getName());

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public void addRoomToHotel(Room room, String hotelID) {
        LOGGER.info("Adding a room to hotel " + hotelID);

        final Hotel hotel = hotelDao.findOne(hotelID);
        final List<Room> rooms = new ArrayList<>(hotel.getHotelRooms());

        if (CollectionUtils.isEmpty(rooms)) {
            LOGGER.debug("Hotel had no rooms, but adding one now. ");
            rooms.add(room);
            hotel.setHotelRooms(rooms);

            hotelDao.save(hotel);
        }

        rooms.add(room);
        hotel.setHotelRooms(rooms);

        Update update = new Update();
        update.set("rooms", rooms);

    }

    @Override
    public void addHotelToOrganization(HotelTO hotelTO, Organization organization) throws UnauthorizedAccessException {
        // Create the hotel entity.
        Hotel newHotel = new Hotel();
        newHotel.setHotelName(hotelTO.getHotelName());
        newHotel.setOrganizationID(organization.getId());

        // Create the contact object.
        Contact contact = new Contact();
        contact.setAddressLine1(hotelTO.getAddressLine1());
        contact.setAddressLine2(hotelTO.getAddressLine2());
        contact.setCityName(hotelTO.getCityName());
        contact.setPostalCode(Integer.parseInt(hotelTO.getPostalCode()));
        contact.setProvidenceCode(hotelTO.getStateCode());

        contact.setOfficeNumber(hotelTO.getOfficePhoneNumber());
        contact.setFaxNumber(hotelTO.getFaxPhoneNumber());

        newHotel.setHotelContact(contact);

        hotelDao.save(newHotel);
    }

    @Override
    public List<HotelTO> getAllHotels() {
        return hotelMapper.transformHotels(hotelDao.findAll());
    }

    @Override
    public Collection<HotelTO> getUserHotels(String organizationID) {
        List<Hotel> hotels = hotelDao.getHotelsByOrganizationID(organizationID);

        return hotelMapper.transformHotels(hotels);
    }

    @Override
    public void addHotelToOrganization(Hotel hotel, Organization organization) throws UnauthorizedAccessException {
        hotelDao.save(hotel);
    }
}
