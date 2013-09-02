package com.staleylabs.resteasy.mapping;

import com.staleylabs.resteasy.domain.Hotel;
import com.staleylabs.resteasy.dto.HotelTO;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Custom mapper that can be used for transferring {@link Hotel} and {@link HotelTO}.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/31/13)
 */

@Component
public class HotelMapper extends ModelMapper {

    private static final Logger LOGGER = Logger.getLogger(HotelMapper.class.getName());

    private final ModelMapper modelMapper = new ModelMapper();

    public HotelMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    /**
     * Transforms a given {@link Hotel} entity into a {@link HotelTO} object.
     *
     * @param hotel Object that needs to be transformed into a presentable front end form.
     * @return A single {@link HotelTO} object.
     */
    public HotelTO transformHotel(Hotel hotel) {
        if (hotel != null) {
            LOGGER.debug("Transforming hotel with ID: " + hotel.getHotelId());

            return modelMapper.map(hotel, HotelTO.class);
        }

        return null;
    }

    /**
     * Transforms a collection of given {@link Hotel} entities into a {@link HotelTO} objects.
     *
     * @param hotels Objects that needs to be transformed into a presentable front end form.
     * @return A collection of {@link HotelTO} objects.
     */
    public List<HotelTO> transformHotels(Collection<Hotel> hotels) {
        if (!CollectionUtils.isEmpty(hotels)) {
            List<HotelTO> transformedList = new ArrayList<>(hotels.size());

            for (Hotel hotel : hotels) {
                transformedList.add(transformHotel(hotel));
            }

            return transformedList;
        }

        return null;
    }

    /**
     * Transforms a given {@link HotelTO} entity into a {@link Hotel} object.
     *
     * @param hotel Object that needs to be transformed into back end form.
     * @return A single {@link Hotel} object.
     */
    public Hotel transformHotelTO(HotelTO hotel) {
        if (hotel != null) {
            LOGGER.debug("Transforming hotel with ID: " + hotel.getHotelID());

            return modelMapper.map(hotel, Hotel.class);
        }

        return null;
    }

    /**
     * Transforms a collection of given {@link HotelTO} entities into a {@link Hotel} objects.
     *
     * @param hotels Objects that needs to be transformed into a back end form.
     * @return A collection of {@link Hotel} objects.
     */
    public List<Hotel> transformHotelTOs(Collection<HotelTO> hotels) {
        if (CollectionUtils.isEmpty(hotels)) {
            List<Hotel> transformedList = new ArrayList<>(hotels.size());

            for (HotelTO hotel : hotels) {
                transformedList.add(transformHotelTO(hotel));
            }

            return transformedList;
        }

        return null;
    }

}
