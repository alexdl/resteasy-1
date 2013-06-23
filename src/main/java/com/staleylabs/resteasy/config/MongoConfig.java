package com.staleylabs.resteasy.config;

import com.mongodb.MongoURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Sean M. Staley
 * @version X.X (6/22/13)
 */

@Configuration
public class MongoConfig {

    @Bean
    public SimpleMongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(getMongoURI());
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    /**
     * Used to return the variable that is being used for the database connection to MongoDB or on Heroku, MongoHQ.
     * @return {@link String} representing the URL to the MongoDB.
     */
    private MongoURI getMongoURI() {
        return new MongoURI(System.getenv("MONGOHQ_URL"));
    }
}
