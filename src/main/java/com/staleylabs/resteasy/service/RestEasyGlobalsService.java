package com.staleylabs.resteasy.service;

import com.staleylabs.resteasy.domain.Global;

import java.util.List;

/**
 * Service that is used to gather and set properties in the application in Domain > Service layer form.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/18/13)
 */

public interface RestEasyGlobalsService {

    /**
     * Gets a property value from the data source.
     *
     * @param key {@link String} form of a property.
     * @return {@link Object} value relating to the property found in the data source. This could be {@code null}.
     */
    Object getPropertyValue(String key);

    /**
     * Used to get a property from the data source with the incoming key. If the key can not be resolved, the value
     * passed into the method in the <code>defaultValue</code> field will be used.
     *
     * @param key   Name of the property requested.
     * @param value Default value that should be displayed if not found in the database.
     * @return {@link Object} value found in database. Will use the default value if not found.
     */
    void setProperty(String key, Object value);

    /**
     * Gets a {@code property entity} from the data source.
     *
     * @param key {@link String} form of a property.
     * @return {@link Global} entity that relates to the key provided to the method.
     */
    Global getPropertyEntity(String key);

    /**
     * Gets all of the properties in the application data source.
     *
     * @return {@link List} of {@link Global} entities from the data source.
     */
    List<Global> getAllProperties();
}
