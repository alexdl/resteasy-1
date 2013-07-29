package com.staleylabs.resteasy.domain.user;

import java.io.Serializable;

/**
 * Bean that represents a user that is registering for the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (7/22/13)
 */

public class RegisteringUser implements Serializable {

    private static final long serialVersionUID = -1058772158280246920L;

    private String username;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String addressLine1;

    private String addressLine2;

    private String cityName;

    private String stateCode;

    private String postalCode;

    private String personalPhoneNumber;

    private String organizationName;

    private String organizationAddressLine1;

    private String organizationAddressLine2;

    private String organizationCityName;

    private String organizationStateCode;

    private String organizationPostalCode;

    private String organizationPhoneNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationAddressLine1() {
        return organizationAddressLine1;
    }

    public void setOrganizationAddressLine1(String organizationAddressLine1) {
        this.organizationAddressLine1 = organizationAddressLine1;
    }

    public String getOrganizationAddressLine2() {
        return organizationAddressLine2;
    }

    public void setOrganizationAddressLine2(String organizationAddressLine2) {
        this.organizationAddressLine2 = organizationAddressLine2;
    }

    public String getOrganizationCityName() {
        return organizationCityName;
    }

    public void setOrganizationCityName(String organizationCityName) {
        this.organizationCityName = organizationCityName;
    }

    public String getOrganizationStateCode() {
        return organizationStateCode;
    }

    public void setOrganizationStateCode(String organizationStateCode) {
        this.organizationStateCode = organizationStateCode;
    }

    public String getOrganizationPostalCode() {
        return organizationPostalCode;
    }

    public void setOrganizationPostalCode(String organizationPostalCode) {
        this.organizationPostalCode = organizationPostalCode;
    }

    public String getPersonalPhoneNumber() {
        return personalPhoneNumber;
    }

    public void setPersonalPhoneNumber(String personalPhoneNumber) {
        this.personalPhoneNumber = personalPhoneNumber;
    }

    public String getOrganizationPhoneNumber() {
        return organizationPhoneNumber;
    }

    public void setOrganizationPhoneNumber(String organizationPhoneNumber) {
        this.organizationPhoneNumber = organizationPhoneNumber;
    }
}
