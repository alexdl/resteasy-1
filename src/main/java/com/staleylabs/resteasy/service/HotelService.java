package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.beans.Room;
import com.staleylabs.resteasy.domain.Hotel;
import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.dto.HotelTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.sun.servicetag.UnauthorizedAccessException;

import java.util.Collection;
import java.util.List;

/**
 * Service that will be used for all Hotel entity things.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/25/13)
 */

public interface HotelService {

    /**
     * Add an instance of a Hotel to an organization.
     *
     * @param hotel        Instance of {@link Hotel} that will be applied to the given organization.
     * @param organization {@link Organization} that the hotel will be applied to.
     */
    void addHotelToOrganization(Hotel hotel, Organization organization) throws UnauthorizedAccessException;

    /**
     * Applies a new room to a hotel specified by the {@code hotelID}.
     *
     * @param room    Room entity that will be applied to a hotel.
     * @param hotelID {@link String} ID of the hotel where the room shall be applied.
     */
    void addRoomToHotel(Room room, String hotelID) throws InsufficientInformationException;

    /**
     * Add an instance of a Hotel TO to an organization. This will transform the existing HotelTO into a {@link Hotel}
     * object to be persisted into the data source.
     *
     * @param hotelTO      Instance of {@link HotelTO} that will be applied to the given organization.
     * @param organization {@link Organization} that the hotel will be applied to.
     */
    void addHotelToOrganization(HotelTO hotelTO, Organization organization) throws UnauthorizedAccessException;

    /**
     * Returns a collection of all of the hotels that are found in the application's data source.
     *
     * @return {@link List} of {@link HotelTO} objects, which represent all of the hotels in the database.
     */
    List<HotelTO> getAllHotels();

    /**
     * Returns a collection of hotels that are part of a given organization.
     *
     * @param organizationID {@code ID} of the organization to return hotels for.
     * @return All hotels that are associated with the organization ID passed into the method.
     */
    Collection<HotelTO> getUserHotels(String organizationID);
}
