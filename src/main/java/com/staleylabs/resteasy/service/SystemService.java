package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.domain.Global;

import java.util.List;
import java.util.Map;

/**
 * Service used for gathering information about the application, application resources, and server information.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/10/13)
 */

public interface SystemService {

    /**
     * Gets all of the system information, including memory, OS version, etc.
     *
     * @return {@link Map} that contains a key regarding what information we is referenced in the object of the map.
     */
    Map<String, String> getSystemInformation();

    /**
     * Get the amount of time that the JVM has been running.
     *
     * @return {@link String} in the form of "1 day, 2 hours, 34 minutes, 16 seconds"
     */
    String getUpTimeInformation();

    /**
     * Obtains all of the system properties found in the application data source.
     *
     * @return {@link List} of {@link Global} property objects that the application uses.
     */
    List<Global> getSystemProperties();
}
