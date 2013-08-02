package com.staleylabs.resteasy.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity representing an organization in the application's data source.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/1/13)
 */
@Document(collection = "organization")
public class Organization {

    @Id
    private String id;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String organizationName;

    private ContactInformation organizationContactInformation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public ContactInformation getOrganizationContactInformation() {
        return organizationContactInformation;
    }

    public void setOrganizationContactInformation(ContactInformation organizationContactInformation) {
        this.organizationContactInformation = organizationContactInformation;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id='" + id + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", organizationContactInformation=" + organizationContactInformation +
                '}';
    }
}
