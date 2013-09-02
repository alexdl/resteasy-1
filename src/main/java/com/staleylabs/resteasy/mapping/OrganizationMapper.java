package com.staleylabs.resteasy.mapping;

import com.staleylabs.resteasy.domain.Organization;
import com.staleylabs.resteasy.dto.OrganizationTO;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Custom mapping from {@link com.staleylabs.resteasy.domain.Organization} entities to
 * {@link com.staleylabs.resteasy.dto.OrganizationTO} objects.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/18/13)
 */

@Component
public class OrganizationMapper extends ModelMapper {

    private static final Logger log = Logger.getLogger(OrganizationMapper.class);

    private ModelMapper modelMapper;

    public OrganizationMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    /**
     * Method that is used to transform a organization object to that of a OrganizationTO object that will be passed
     * back to the end user's request.
     *
     * @param organization Object retrieved from the data source that corresponds to a
     *                     {@link com.staleylabs.resteasy.domain.Organization} entity.
     * @return {@link com.staleylabs.resteasy.dto.OrganizationTO} that has the properties that were in the
     *         incoming {@link com.staleylabs.resteasy.domain.Organization} object.
     */
    public OrganizationTO transformOrganization(Organization organization) {
        if (organization != null) {
            log.debug("Transforming user object with ID of " + organization.getId());

            return modelMapper.map(organization, OrganizationTO.class);
        }

        log.info("User object was null and was not transformed.");

        return null;
    }

    /**
     * Returns a list of organization entities into a list of organizationTO objects. This will maintain original order
     * of the list.
     *
     * @param all List of organization objects.
     * @return List of flattened organization objects that are in FIFO order.
     */
    public List<OrganizationTO> transformOrganizations(List<Organization> all) {
        List<OrganizationTO> newList = new LinkedList<>();

        for (Organization organization : all) {
            newList.add(transformOrganization(organization));
        }

        return newList;
    }

    /**
     * Transforms an {@link OrganizationTO} object to a {@link Organization} entity to store in the database.
     *
     * @param organizationTO OrganizationTO that will be morphed.
     * @return {@link Organization} form of the TO that was passed into the method.
     */
    public Organization transformOrganizationTO(OrganizationTO organizationTO) {
        return modelMapper.map(organizationTO, Organization.class);
    }
}