package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.domain.Reservation;
import com.staleylabs.resteasy.dto.ReservationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;

/**
 * Service layer for the reservation portion of the application.
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

    /**
     * Updates a reservation currently in the data source.
     *
     * @param reservation {@link ReservationTO} object that must contain the reservation's ID and the information
     *                    that is to be updated in the data source.
     */
    void updateReservation(ReservationTO reservation) throws InsufficientInformationException;

    /**
     * Updates a reservation currently in the data source.
     *
     * @param reservation {@link Reservation} object that must contain the reservation's ID and the information
     *                    that is to be updated in the data source.
     */
    void updateReservation(Reservation reservation) throws InsufficientInformationException;

    /**
     * Removes a given reservation with the ID provided from the application's data source. It is assumed that this ID
     * will be not {@code NULL} and valid.
     *
     * @param reservationID {@link String} {@code ID} of the reservation.
     */
    void removeReservation(String reservationID);

    /**
     * Displays a given reservation by ID to the controller layer of the application.
     *
     * @param reservationID {@code ID} of the reservation persisted in the data source.
     * @return {@link ReservationTO} object that corresponds to the incoming ID.
     */
    ReservationTO viewReservation(String reservationID);
}
