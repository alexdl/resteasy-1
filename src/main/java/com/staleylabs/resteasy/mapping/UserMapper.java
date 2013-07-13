package com.staleylabs.resteasy.mapping;

import com.staleylabs.resteasy.domain.user.User;
import com.staleylabs.resteasy.dto.UserTO;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Used to map an entity bean to the bean sent to the UI or end user.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/16/13)
 */

@Component
public class UserMapper extends ModelMapper {

    private static final Logger log = Logger.getLogger(UserMapper.class);

    private ModelMapper modelMapper;

    public UserMapper() {
        modelMapper = new ModelMapper();
    }

    /**
     * Method that is used to transform a user object to that of a UserTO object that will be passed back to the end
     * user's request.
     *
     * @param user Object retrieved from the data source that corresponds to a {@link User} entity.
     * @return {@link UserTO} that has the properties that were in the incoming {@link User} object.
     */
    public UserTO transformUser(User user) {

        if (user != null) {
            log.debug("Transforming user object with ID of " + user.getId());

            return modelMapper.map(user, UserTO.class);
        }

        log.info("User object was null and was not transformed.");

        return null;
    }
}
