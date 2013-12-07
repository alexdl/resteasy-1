package com.staleylabs.resteasy.commons;

/**
 * Status types of a reservation in the system.
 *
 * @author Sean M. Staley
 * @version 1.0 (11/22/13)
 */

public enum ReservationStatus {

    CANCELED(0),
    COMPLETED(1),
    IN_PROGRESS(2),
    SCHEDULED(3);

    private int statusCode;

    ReservationStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public String toString() {
        return "ReservationStatus{" +
                "statusCode=" + statusCode +
                '}';
    }
}
