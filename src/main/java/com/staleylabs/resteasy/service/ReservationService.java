package com.staleylabs.resteasy.service;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/25/13)
 */

@Service
public interface ReservationService {

    /**
     * Used to create a given reservation for a customer.
     */
    void createReservation();
}
