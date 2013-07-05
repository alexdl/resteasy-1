package com.staleylabs.resteasy.config;

import com.mongodb.MongoURI;
import com.mongodb.WriteConcern;
import com.staleylabs.resteasy.commons.MongoCommons;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Spring bean configuration class for the MongoDB creation in the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/22/13)
 */

@Configuration
public class MongoConfig {

    private static final Logger LOG = Logger.getLogger(MongoConfig.class.getName());

    private static MongoURI MONGO_URI_INSTANCE;

    /**
     * Initializes the MongoDBFactory so that the application can utilize the {@link MongoTemplate} functionality.
     *
     * @return A new singleton instance of {@link SimpleMongoDbFactory}.
     * @throws Exception Generic exception that is inherited from the newly created object.
     */
    @Bean
    public SimpleMongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory((MONGO_URI_INSTANCE != null) ? MONGO_URI_INSTANCE : getMongoURI());
    }

    /**
     * Initializes a new instance of {@link MongoTemplate}, which enables the application to use Spring Data's MongoDB
     * functionality to access the database.
     *
     * @return New singleton instance of {@link MongoTemplate} to use to access the database.
     * @throws Exception Generic exception that is inherited from the newly created object.
     */
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        mongoTemplate.setWriteConcern(WriteConcern.SAFE);

        return mongoTemplate;
    }

    /**
     * Used to return the variable that is being used for the database connection to MongoDB or on Heroku, MongoHQ.
     *
     * @return {@link String} representing the URL to the MongoDB.
     */
    private MongoURI getMongoURI() {
        LOG.info("Initializing database connection.");

        String databaseURL = System.getenv(MongoCommons.MONGO_URL_PROPERTY);

        if (databaseURL == null || databaseURL.isEmpty()) {
            databaseURL = MongoCommons.DEFAULT_MONGO_URL;
        }

        LOG.info("Connecting to database with URL: " + databaseURL);

        MONGO_URI_INSTANCE = new MongoURI(databaseURL);

        return MONGO_URI_INSTANCE;
    }
}
