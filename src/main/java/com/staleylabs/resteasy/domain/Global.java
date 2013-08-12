package com.staleylabs.resteasy.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Bean that represents a property found in the application data source.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/11/13)
 */
@Document(collection = "property")
public class Global implements Serializable {

    @Id
    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String key;

    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
