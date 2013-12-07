package com.staleylabs.resteasy.dao;

import com.staleylabs.resteasy.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Data source accessing class that is used for the reservation entity type.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/25/13)
 */

@Repository
public interface ReservationDao extends CrudRepository<Reservation, String> {
}
