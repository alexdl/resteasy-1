package com.staleylabs.resteasy.dao;

import com.staleylabs.resteasy.domain.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data source accessing class that is used for the hotel entity type.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/25/13)
 */

@Repository
public interface HotelDao extends MongoRepository<Hotel, String> {

    /**
     * Obtains a collection of hotels given the ID of an organization from the application's data source.
     *
     * @param organizationID {@code ID} of an organization that has one or more hotels.
     * @return {@link List} of {@link Hotel} objects from the application data source.
     */
    List<Hotel> getHotelsByOrganizationID(String organizationID);
}
