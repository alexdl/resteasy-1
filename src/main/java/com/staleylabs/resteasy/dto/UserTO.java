package com.staleylabs.resteasy.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Object that represents the current user logged into the application. This is the form that will typically show to the
 * view, or end users using the APIs provided by the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/2/13)
 * @see com.staleylabs.resteasy.domain.user.User
 */
@JsonSerialize
public class UserTO implements Serializable {

    private String id;

    private String username;

    private String firstName;

    private String middleName;

    private String lastName;

    private String emailAddress;

    private boolean enabled;

    private long creationDate;

    private long lastLoggedIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(long lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
