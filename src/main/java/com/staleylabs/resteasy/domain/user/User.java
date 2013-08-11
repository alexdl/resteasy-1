package com.staleylabs.resteasy.domain.user;

import com.staleylabs.resteasy.domain.ContactInformation;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Entity representing a single user found in the application's data source.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/2/13)
 */

@Document(collection = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -2162494873842344487L;

    @Id
    private String id;

    @Indexed(direction = IndexDirection.ASCENDING, unique = true)
    private String username;

    private String firstName;

    private String middleName;

    private String lastName;

    private ContactInformation contactInformation;

    @Indexed(unique = true)
    private String emailAddress;

    private String organizationId;

    private String password;

    private boolean enabled;

    private long lastLoggedIn;

    private long creationDate;

    private int role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation address) {
        this.contactInformation = contactInformation;
    }

    public long getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(long lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
