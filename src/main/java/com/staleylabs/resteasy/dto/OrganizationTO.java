package com.staleylabs.resteasy.dto;

import java.io.Serializable;

/**
 * Object that is represented as an Organization entity instead of the actual {@link com.staleylabs.resteasy.domain.Organization}
 * object, which contains TMI for the requesting end user.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/18/13)
 */
public class OrganizationTO implements Serializable {

    private String organizationName;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String stateCode;

    private String countryCode;

    private int postalCode;

    private long officePhoneNumber;

    private long faxPhoneNumber;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public long getOfficePhoneNumber() {
        return officePhoneNumber;
    }

    public void setOfficePhoneNumber(long officePhoneNumber) {
        this.officePhoneNumber = officePhoneNumber;
    }

    public long getFaxPhoneNumber() {
        return faxPhoneNumber;
    }

    public void setFaxPhoneNumber(long faxPhoneNumber) {
        this.faxPhoneNumber = faxPhoneNumber;
    }
}
