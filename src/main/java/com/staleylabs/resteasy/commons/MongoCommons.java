package com.staleylabs.resteasy.commons;

/**
 * Class that contains all of the values used by the Mongo database in the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/15/13)
 */

public class MongoCommons {

    /* Database Connection and Property Settings */
    public static final String MONGO_URL_PROPERTY = "MONGOHQ_URL";

    public static final String DEFAULT_MONGO_URL = "mongodb://localhost/resteasy";

    /* Collections inside of the database */
    public static final String ORGANIZATION_COLLECTION = "organization";

    public static final String USER_COLLECTION = "user";
}
