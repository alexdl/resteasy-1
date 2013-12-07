package com.staleylabs.resteasy.mapping;

import com.staleylabs.resteasy.domain.Reservation;
import com.staleylabs.resteasy.dto.ReservationTO;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Custom mapping for the reservation objects.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Component
public class ReservationMapper extends ModelMapper {

    private static final Logger log = Logger.getLogger(ReservationMapper.class.getName());

    private final ModelMapper modelMapper;

    /**
     * Default constructor for the {@link ReservationMapper} object.
     */
    public ReservationMapper() {
        modelMapper = new ModelMapper();
    }

    /**
     * Transforms a {@link Reservation} object to a front end {@link ReservationTO}.
     *
     * @param reservation Object that will be transformed.
     * @return {@link ReservationTO} that contains the same properties of the passed into the method.
     */
    public ReservationTO transformReservation(Reservation reservation) {
        log.debug("Transforming Reservation object with ID " + reservation.getReservationID());
        return modelMapper.map(reservation, ReservationTO.class);
    }

    /**
     * Transforms a {@link ReservationTO} object to a back end {@link Reservation}.
     *
     * @param reservation Object that will be transformed.
     * @return {@link Reservation} that contains the same properties of the passed into the method.
     */
    public Reservation transformReservation(ReservationTO reservation) {
        return modelMapper.map(reservation, Reservation.class);
    }

    /**
     * Transforms a collection of {@link Reservation} objects to front end {@link ReservationTO} objects.
     *
     * @param reservations {@link Collection} of {@link Reservation} objects that will be transformed.
     * @return {@link Collection} of {@link ReservationTO} that represent the objects injected into the method. If the
     *         {@code collection} of objects is empty or {@code null}, the method will return {@code null}.
     */
    public Collection<ReservationTO> transformReservations(Collection<Reservation> reservations) {
        if (!CollectionUtils.isEmpty(reservations)) {
            LogMF.debug(log, "Beginning to transform (0) Reservation objects.", reservations.size());

            List<ReservationTO> result = new ArrayList<>(reservations.size());

            for (Reservation reservation : reservations) {
                result.add(transformReservation(reservation));
            }

            return result;
        }
        log.info("Reservation collection did not have any objects to morph.");

        return null;
    }

    /**
     * Transforms a collection of {@link ReservationTO} objects to front end {@link Reservation} objects.
     *
     * @param reservations {@link List} of {@link ReservationTO} objects that will be transformed.
     * @return {@link Collection} of {@link Reservation} that represent the objects injected into the method. If the
     *         {@code collection} of objects is empty or {@code null}, the method will return {@code null}.
     */
    public Collection<Reservation> transformReservations(List<ReservationTO> reservations) {
        if (!CollectionUtils.isEmpty(reservations)) {
            List<Reservation> result = new ArrayList<>(reservations.size());

            for (ReservationTO reservation : reservations) {
                result.add(transformReservation(reservation));
            }

            return result;
        }
        log.info("ReservationTO collection did not have any objects to morph.");

        return null;
    }
}
