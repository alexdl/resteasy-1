package com.staleylabs.resteasy.dao;

import com.staleylabs.resteasy.domain.Global;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Method used to access all of the global values in the application's data source.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/11/13)
 */
@Repository
@Transactional
public interface RestEasyGlobalsDao extends MongoRepository<Global, String> {
}
