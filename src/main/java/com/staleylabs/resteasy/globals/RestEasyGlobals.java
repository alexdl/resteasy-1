package com.staleylabs.resteasy.globals;

import com.staleylabs.resteasy.dao.RestEasyGlobalsDao;
import com.staleylabs.resteasy.domain.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class that is used in the application to gather application specific properties.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/11/13)
 */
@Component
public final class RestEasyGlobals {

    @Autowired
    private RestEasyGlobalsDao restEasyGlobalsDao;

    /**
     * Used to get a property from the data source with the incoming key.
     *
     * @param key Name of the property requested.
     * @return {@link Object} value that corresponds to the key in place.
     */
    public Object getProperty(String key) {
        return restEasyGlobalsDao.findOne(key).getValue();
    }

    /**
     * Used to get a property from the data source with the incoming key.
     *
     * @param key Name of the property requested.
     * @return {@link String} value that corresponds to the key in place.
     */
    public String getStringProperty(String key) {
        return restEasyGlobalsDao.findOne(key).getValue().toString();
    }

    /**
     * Used to get a property from the data source with the incoming key.
     *
     * @param key Name of the property requested.
     * @return <code>integer</code> value that corresponds to the key in place.
     */
    public int getIntegerProperty(String key) {
        return (int) restEasyGlobalsDao.findOne(key).getValue();
    }

    /**
     * Used to get a property from the data source with the incoming key.
     *
     * @param key Name of the property requested.
     * @return <code>boolean</code> value that corresponds to the key in place.
     */
    public boolean getBooleanProperty(String key) {
        return (boolean) restEasyGlobalsDao.findOne(key).getValue();
    }

    /**
     * Used to get a property from the data source with the incoming key.
     *
     * @param key Name of the property requested.
     * @return <code>double</code> value that corresponds to the key in place.
     */
    public double getDoubleProperty(String key) {
        return (double) restEasyGlobalsDao.findOne(key).getValue();
    }

    /**
     * Used to get a property from the data source with the incoming key. If the key can not be resolved, the value
     * passed into the method in the <code>defaultValue</code> field will be used.
     *
     * @param key          Name of the property requested.
     * @param defaultValue Default value that should be displayed if not found in the database.
     * @return {@link Object} value found in database. Will use the default value if not found.
     */
    public Object getPropertyWithDefault(String key, Object defaultValue) {
        Global property = restEasyGlobalsDao.findOne(key);

        if (property != null) {
            return property.getValue();
        }

        return defaultValue;
    }

    /**
     * Used to get a property from the data source with the incoming key. If the key can not be resolved, the value
     * passed into the method in the <code>defaultValue</code> field will be used.
     *
     * @param key          Name of the property requested.
     * @param defaultValue Default value that should be displayed if not found in the database.
     * @return {@link Object} value found in database. Will use the default value if not found.
     */
    public String getStringPropertyWithDefault(String key, String defaultValue) {
        Global property = restEasyGlobalsDao.findOne(key);

        if (property != null) {
            return property.toString();
        }

        return defaultValue;
    }

    /**
     * Used to get a property from the data source with the incoming key. If the key can not be resolved, the value
     * passed into the method in the <code>defaultValue</code> field will be used.
     *
     * @param key          Name of the property requested.
     * @param defaultValue Default value that should be displayed if not found in the database.
     * @return {@link Object} value found in database. Will use the default value if not found.
     */
    public int getIntegerPropertyWithDefault(String key, int defaultValue) {
        Global property = restEasyGlobalsDao.findOne(key);

        if (property != null) {
            return (int) property.getValue();
        }

        return defaultValue;
    }

    /**
     * Used to get a property from the data source with the incoming key. If the key can not be resolved, the value
     * passed into the method in the <code>defaultValue</code> field will be used.
     *
     * @param key          Name of the property requested.
     * @param defaultValue Default value that should be displayed if not found in the database.
     * @return {@link Object} value found in database. Will use the default value if not found.
     */
    public double getDoublePropertyWithDefault(String key, double defaultValue) {
        Global property = restEasyGlobalsDao.findOne(key);

        if (property != null) {
            return (double) property.getValue();
        }

        return defaultValue;
    }

    /**
     * Used to get a property from the data source with the incoming key. If the key can not be resolved, the value
     * passed into the method in the <code>defaultValue</code> field will be used.
     *
     * @param key          Name of the property requested.
     * @param defaultValue Default value that should be displayed if not found in the database.
     * @return <code>boolean</code> value found in database. Will use the default value if not found.
     */
    public boolean getBooleanPropertyWithDefault(String key, boolean defaultValue) {
        Global property = restEasyGlobalsDao.findOne(key);

        if (property != null) {
            return (boolean) property.getValue();
        }

        return defaultValue;
    }

    /**
     * Used to get a property from the data source with the incoming key. If the key can not be resolved, the value
     * passed into the method in the <code>defaultValue</code> field will be used.
     *
     * @param key   Name of the property requested.
     * @param value Default value that should be displayed if not found in the database.
     * @return {@link Object} value found in database. Will use the default value if not found.
     */
    public synchronized void setProperty(String key, String value) {
        Global property = restEasyGlobalsDao.findOne(key);
        if (property == null) {

            // Create a new one and then save to the application data source.
            property = new Global();
            property.setKey(key);
            property.setValue(value);

            restEasyGlobalsDao.save(property);
        } else {
            // Set the value of the old property.
            property.setValue(value);

            // Update the property in the data source.
            restEasyGlobalsDao.delete(key);
            restEasyGlobalsDao.save(property);
        }
    }
}
