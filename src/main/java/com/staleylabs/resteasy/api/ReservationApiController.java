package com.staleylabs.resteasy.api;

import com.staleylabs.resteasy.dto.ReservationTO;
import com.staleylabs.resteasy.exception.InsufficientInformationException;
import com.staleylabs.resteasy.service.ReservationService;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.http.HttpStatus.*;

/**
 * RESTful APIs for all things regarding the reservation system of the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Controller
@RequestMapping(value = "/api/reservation")
public class ReservationApiController {

    private static final Logger LOGGER = Logger.getLogger(ReservationApiController.class.getName());

    @Autowired
    private ReservationService reservationService;

    /**
     * Creates a new reservation based on the reservationTO object passed into the API call.
     *
     * @param reservation Instance of the reservation to be created.
     * @param response    Instance of a HTTP response object.
     * @throws IOException Occurs when there is an issue with persisting the object to the data source.
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(CREATED)
    public void createNewReservation(@RequestBody ReservationTO reservation, HttpServletResponse response) throws IOException {
        LOGGER.debug("Creating new reservation...");

        final long timestamp = new Date().getTime();
        reservation.setCreationDate(timestamp);
        reservation.setLastModifiedDate(timestamp);

        try {
            reservationService.createNewReservation(reservation);
        } catch (InsufficientInformationException e) {
            response.sendError(BAD_REQUEST.value(), e.getMessage());
        }
    }

    /**
     * API call to modify an already existing reservation in the data source. This method requires a reservation object
     * that contains the ID of a given reservation all up to date values.
     *
     * @param reservation Instance of the reservation that contains the ID and any changed information.
     * @param response    Instance of a HTTP response object.
     * @throws IOException Occurs when there is an issue with persisting the object to the data source.
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(OK)
    public void updateReservation(@RequestBody ReservationTO reservation, HttpServletResponse response) throws IOException {
        LOGGER.debug("Updating reservation...");

        try {
            reservationService.updateReservation(reservation);
        } catch (InsufficientInformationException e) {
            response.sendError(BAD_REQUEST.value(), e.getMessage());
        }
    }

    /**
     * API call used to cancel a given reservation that currently exists in the application's data source.
     *
     * @param reservationID ID of the reservation to be canceled.
     * @param response      Instance of a HTTP response object.
     * @throws IOException Occurs when there is an issue with persisting the object to the data source.
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(NO_CONTENT)
    public void cancelReservation(@RequestBody String reservationID, HttpServletResponse response) throws IOException {
        LogMF.debug(LOGGER, "Removing reservation {0}", reservationID);

        if (isNotBlank(reservationID)) {
            reservationService.removeReservation(reservationID);
        } else {
            LOGGER.warn("Reservation ID was empty. Please pass through a reservation ID with the call.");
        }
    }

    /**
     * API call to modify an already existing reservation in the data source. This method requires a reservation object
     * that contains the ID of a given reservation all up to date values.
     *
     * @param reservation Instance of the reservation that contains the ID and any changed information.
     * @param response    Instance of a HTTP response object.
     * @throws IOException Occurs when there is an issue with persisting the object to the data source.
     */
    @RequestMapping(value = "/{reservationID}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(OK)
    @ResponseBody
    public ReservationTO getReservation(@PathVariable String reservationID, HttpServletResponse response) throws IOException {
        LogMF.debug(LOGGER, "Grabbing reservation {0} for viewing.", reservationID);

        if (isBlank(reservationID)) {
            LOGGER.warn("Reservation ID was empty.");
            return null;
        }

        return reservationService.viewReservation(reservationID);
    }

}
