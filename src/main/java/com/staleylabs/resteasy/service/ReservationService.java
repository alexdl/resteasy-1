package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.dto.ReservationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/25/13)
 */

public interface ReservationService {

    /**
     * Creates a new reservation in the system.
     *
     * @param reservation Details of the newly created reservation.
     */
    void createNewReservation(ReservationTO reservation) throws InsufficientInformationException;
}
