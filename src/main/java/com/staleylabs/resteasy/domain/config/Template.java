package com.staleylabs.resteasy.domain.config;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity used for custom configuration in the application where a given {@link com.staleylabs.resteasy.domain.Hotel}
 * can maintain its own templates, such as <b>emails</b> or <b>receipts</b>.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/7/13)
 */

@Document(collection = "template")
public class Template {

    @Id
    private String id;

    private String hotelId;

    private String type;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + hotelId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Template)) {
            return false;
        }

        Template template = (Template) o;

        if (!hotelId.equals(template.hotelId)) {
            return false;
        }
        if (!id.equals(template.id)) {
            return false;
        }
        if (!type.equals(template.type)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id='" + id + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
