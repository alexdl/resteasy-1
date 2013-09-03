package com.staleylabs.resteasy.mapping;

import com.staleylabs.resteasy.domain.Reservation;
import com.staleylabs.resteasy.dto.ReservationTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * StaleyLabs
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

public class ReservationMapperTest {

    private static final String RESERVATION_ID = "1";

    private static final String HOTEL_ID = "100";

    private static final double ADDITIONAL_COST = 10.00;

    private static final double BASE_COST = 10.00;

    private static final String CUSTOMER_ID = "50";

    private static final double TAX_COST = 3.60;

    private ReservationMapper reservationMapper;

    private Reservation singleReservation;

    private ReservationTO singleReservationTO;

    private Collection<Reservation> reservationCollection;

    private List<ReservationTO> reservationTOList;

    @Before
    public void setUp() throws Exception {
        reservationMapper = new ReservationMapper();

        singleReservation = buildEntity();
        singleReservationTO = buildSingleTO();

        reservationCollection = buildReservationList();
        reservationTOList = buildReservationTOList();
    }

    @After
    public void tearDown() throws Exception {
        singleReservation = null;
        singleReservationTO = null;

        reservationCollection = null;
        reservationTOList = null;
    }

    @Test
    public void testTransformReservation_entity_to_TO() throws Exception {
        ReservationTO actual = reservationMapper.transformReservation(singleReservation);

        assertEquals(BASE_COST, actual.getBaseCost(), 0);
        assertEquals(RESERVATION_ID, actual.getReservationID());
    }

    @Test
    public void testTransformReservation_TO_to_entity() throws Exception {
        Reservation actual = reservationMapper.transformReservation(singleReservationTO);

        assertEquals(BASE_COST, actual.getBaseCost(), 0);
        assertEquals(RESERVATION_ID, actual.getReservationID());
    }

    @Test
    public void testTransformReservations_entity_to_TO() throws Exception {
        Collection<ReservationTO> actual = reservationMapper.transformReservations(reservationCollection);

        assertEquals(5, actual.size());
    }

    @Test
    public void testTransformReservations_TO_to_entity() throws Exception {
        Collection<Reservation> actual = reservationMapper.transformReservations(reservationTOList);

        assertEquals(5, actual.size());
    }

    @Test
    public void testTransformReservations_entity_to_TO_empty() throws Exception {
        Collection<ReservationTO> actual = reservationMapper.transformReservations(new ArrayList<Reservation>());

        assertNull(actual);
    }

    @Test
    public void testTransformReservations_TO_to_entity_empty() throws Exception {
        Collection<Reservation> actual = reservationMapper.transformReservations(new ArrayList<ReservationTO>());

        assertNull(actual);
    }

    @Test
    public void testTransformReservations_entity_to_TO_null() throws Exception {
        Collection<ReservationTO> actual = reservationMapper.transformReservations((Collection<Reservation>) null);

        assertNull(actual);
    }

    @Test
    public void testTransformReservations_TO_to_entity_null() throws Exception {
        Collection<Reservation> actual = reservationMapper.transformReservations((List<ReservationTO>) null);

        assertNull(actual);
    }

    /**
     * Builds out a mock reservation entity.
     *
     * @return {@link Reservation} object that contains some base information for testing.
     */
    private Reservation buildEntity() {
        Reservation result = new Reservation();
        result.setReservationID(RESERVATION_ID);
        result.setHotelID(HOTEL_ID);
        result.setAdditionalCost(ADDITIONAL_COST);
        result.setBaseCost(BASE_COST);
        result.setCustomerID(CUSTOMER_ID);
        result.setTaxCost(TAX_COST);

        return result;
    }

    /**
     * Builds out a mock reservation transfer object for testing.
     *
     * @return {@link ReservationTO} object that contains some base information for testing.
     */
    private ReservationTO buildSingleTO() {
        ReservationTO reservationTO = new ReservationTO();
        reservationTO.setTaxCost(TAX_COST);
        reservationTO.setAdditionalCost(ADDITIONAL_COST);
        reservationTO.setHotelID(HOTEL_ID);
        reservationTO.setBaseCost(BASE_COST);
        reservationTO.setReservationID(RESERVATION_ID);

        return reservationTO;
    }

    /**
     * Builds out a list of reservation entities for test.
     *
     * @return {@link List} of {@link Reservation} entities for testing.
     */
    private List<Reservation> buildReservationList() {
        List<Reservation> result = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            result.add(buildEntity());
        }

        return result;
    }

    /**
     * Builds out a list of reservation entities for test.
     *
     * @return {@link List} of {@link ReservationTO} entities for testing.
     */
    private List<ReservationTO> buildReservationTOList() {
        List<ReservationTO> result = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            result.add(buildSingleTO());
        }

        return result;
    }
}
