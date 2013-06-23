package com.staleylabs.resteasy.config;

import com.mongodb.DB;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Sean M. Staley
 * @version X.X (6/22/13)
 */

@Configuration
public class MongoConfiguration {

    @Bean
    public DB getDb() throws UnknownHostException, MongoException {
        MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
        DB db = mongoURI.connectDB();
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());

        return db;
    }
}
