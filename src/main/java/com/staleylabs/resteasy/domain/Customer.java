package com.staleylabs.resteasy.domain;

import com.staleylabs.resteasy.beans.Contact;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Entity that represents a given reserving customer.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/24/13)
 */
@Document(collection = "customer")
public class Customer implements Serializable {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Contact customerContact;

    private String emailAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contact getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(Contact customerContact) {
        this.customerContact = customerContact;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
