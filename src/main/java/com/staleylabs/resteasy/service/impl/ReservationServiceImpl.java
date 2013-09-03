package com.staleylabs.resteasy.service.impl;

import com.staleylabs.resteasy.dao.ReservationDao;
import com.staleylabs.resteasy.domain.Reservation;
import com.staleylabs.resteasy.dto.ReservationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.mapping.CustomerMapper;
import com.staleylabs.resteasy.mapping.ReservationMapper;
import com.staleylabs.resteasy.service.CustomerService;
import com.staleylabs.resteasy.service.ReservationService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link ReservationService} interface.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Service("reservationServiceImpl")
public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = Logger.getLogger(ReservationServiceImpl.class.getName());

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

        if (StringUtils.isNotBlank(entity.getCustomerID())) {
            String customerID = customerService.createNewCustomer(customerMapper.transformCustomer(reservation.getCustomer()));
            entity.setCustomerID(customerID);
        }

        reservationDao.save(entity);
    }
}
