package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.commons.ReservationStatus;
import com.staleylabs.resteasy.dao.ReservationDao;
import com.staleylabs.resteasy.domain.Reservation;
import com.staleylabs.resteasy.dto.ReservationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.mapping.CustomerMapper;
import com.staleylabs.resteasy.mapping.ReservationMapper;
import com.staleylabs.resteasy.service.CustomerService;
import com.staleylabs.resteasy.service.ReservationService;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Implementation of the {@link ReservationService} interface.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Service("reservationServiceImpl")
public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = Logger.getLogger(ReservationServiceImpl.class.getName());

    private static final Date DATE = new Date();

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public void createNewReservation(ReservationTO reservation) throws InsufficientInformationException {
        LogMF.info(log, "Creating a new reservation for hotel (0).", reservation.getHotelID());

        Reservation entity = reservationMapper.transformReservation(reservation);

        if (isNotBlank(entity.getCustomerID())) {
            String customerID = customerService.createNewCustomer(customerMapper.transformCustomer(reservation.getCustomer()));
            entity.setCustomerID(customerID);
        }

        entity.setReservationStatus(ReservationStatus.SCHEDULED.getStatusCode());

        reservationDao.save(entity);
    }

    @Override
    public void updateReservation(ReservationTO reservation) throws InsufficientInformationException {
        LogMF.info(log, "Updating reservationTO {0}.", reservation.getReservationID());

        updateReservation(reservationMapper.transformReservation(reservation));
    }

    @Override
    public void updateReservation(Reservation reservation) throws InsufficientInformationException {
        LogMF.info(log, "Updating reservation {0}.", reservation.getReservationID());
        reservation.setLastModifiedDate(DATE.getTime());

        reservationDao.save(reservation);
    }

    @Override
    public void removeReservation(String reservationID) {
        final Reservation entity = reservationDao.findOne(reservationID);
        entity.setReservationStatus(ReservationStatus.CANCELED.getStatusCode());

        try {
            updateReservation(entity);
        } catch (InsufficientInformationException ignored) {
        }
    }

    @Override
    public ReservationTO viewReservation(String reservationID) {
        final Reservation entity = reservationDao.findOne(reservationID);

        return reservationMapper.transformReservation(entity);
    }
}
