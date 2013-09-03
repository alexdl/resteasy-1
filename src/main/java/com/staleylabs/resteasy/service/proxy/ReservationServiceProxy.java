package com.staleylabs.resteasy.service.proxy;

import com.staleylabs.resteasy.dto.ReservationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.service.ReservationService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (StringUtils.isNotBlank(reservation.getHotelID()) && StringUtils.isNotBlank(reservation.getRoomName())
                && (reservation.getCustomerCheckIn() != null && reservation.getCustomerCheckOut() != null)
                && (reservation.getCustomer() != null && StringUtils.isNotBlank(reservation.getCustomer().getEmailAddress())
                && StringUtils.isNotBlank(reservation.getCustomer().getFirstName()))) {
            reservationServiceImpl.createNewReservation(reservation);
        } else {
            throw new InsufficientInformationException("Reservation did not contain enough information.");
        }

    }
}
