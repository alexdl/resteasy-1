package com.staleylabs.resteasy.domain;

import com.staleylabs.resteasy.beans.Contact;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity that represents a given reserving customer.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/24/13)
 */
@Document(collection = "customer")
public class Customer {

    @Id
    private String customerID;

    @Indexed(unique = false, dropDups = false, direction = IndexDirection.ASCENDING)
    private String firstName;

    @Indexed(unique = false, dropDups = false, direction = IndexDirection.ASCENDING)
    private String lastName;

    private Contact customerContact;

    private String emailAddress;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }

        Customer customer = (Customer) o;

        if (customerID != null ? !customerID.equals(customer.customerID) : customer.customerID != null) {
            return false;
        }
        if (!emailAddress.equals(customer.emailAddress)) {
            return false;
        }
        if (!firstName.equals(customer.firstName)) {
            return false;
        }
        if (!lastName.equals(customer.lastName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerID != null ? customerID.hashCode() : 0;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + emailAddress.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
