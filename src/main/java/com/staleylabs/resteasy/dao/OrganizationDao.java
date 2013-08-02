package com.staleylabs.resteasy.dao;

import com.staleylabs.resteasy.domain.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Data source accessing class that is used for the organization bean type.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/1/13)
 */
@Repository
public interface OrganizationDao extends MongoRepository<Organization, String> {

    /**
     * Accesses the data source by the name of the given organization.
     *
     * @param organizationName {@link String} representation of the organization's name.
     * @return {@link Organization} object with the corresponding organization name.
     */
    public Organization getOrganizationByOrganizationName(String organizationName);

}
