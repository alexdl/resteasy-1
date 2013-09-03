package com.staleylabs.resteasy.dao;

import com.staleylabs.resteasy.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Data accessing layer for the {@link com.staleylabs.resteasy.domain.Customer} entity type.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

@Repository
public interface CustomerDao extends MongoRepository<Customer, String> {
}
