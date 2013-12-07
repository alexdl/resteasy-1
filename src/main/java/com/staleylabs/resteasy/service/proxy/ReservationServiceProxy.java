package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.domain.Reservation;
import com.staleylabs.resteasy.dto.ReservationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.service.ReservationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Proxy class for a the {@link ReservationService} interface.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Service("reservationService")
public class ReservationServiceProxy implements ReservationService {

    private static final Logger LOG = Logger.getLogger(ReservationServiceProxy.class.getName());

    @Autowired
    private ReservationService reservationServiceImpl;

    @Override
    public void createNewReservation(ReservationTO reservation) throws InsufficientInformationException {
        LOG.debug("Validating we can create a new reservation with object provided.");

        if (isNotBlank(reservation.getHotelID()) && isNotBlank(reservation.getRoomName())
                && (reservation.getCustomerCheckIn() != null && reservation.getCustomerCheckOut() != null)
                && (reservation.getCustomer() != null && isNotBlank(reservation.getCustomer().getEmailAddress())
                && isNotBlank(reservation.getCustomer().getFirstName()))) {
            reservationServiceImpl.createNewReservation(reservation);
        } else {
            throw new InsufficientInformationException("Reservation did not contain enough information.");
        }

    }

    @Override
    public void updateReservation(ReservationTO reservation) throws InsufficientInformationException {
        if (isBlank(reservation.getReservationID())) {
            throw new InsufficientInformationException("Reservation ID was empty. This is required for processing.");
        }

        reservationServiceImpl.updateReservation(reservation);
    }

    @Override
    public void updateReservation(Reservation reservation) throws InsufficientInformationException {
        if (isBlank(reservation.getReservationID())) {
            throw new InsufficientInformationException("Reservation ID was empty. This is required for processing.");
        }

        reservationServiceImpl.updateReservation(reservation);
    }

    @Override
    public void removeReservation(String reservationID) {
        reservationServiceImpl.removeReservation(reservationID);
    }

    @Override
    public ReservationTO viewReservation(String reservationID) {
        return reservationServiceImpl.viewReservation(reservationID);
    }
}
