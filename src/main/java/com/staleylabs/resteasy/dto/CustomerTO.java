package com.staleylabs.resteasy.dto;

/**
 * Entity that represents a given customer for the front end to work with.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

public class CustomerTO {

    private String customerID;

    private String firstName;

    private String lastName;

    private String addressLine1;

    private String addressLine2;

    private String cityName;

    private String providenceCode;

    private int postalCode;

    private long homePhoneNumber;

    private long officePhoneNumber;

    private long cellPhoneNumber;

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

    public String getProvidenceCode() {
        return providenceCode;
    }

    public void setProvidenceCode(String providenceCode) {
        this.providenceCode = providenceCode;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public long getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(long homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public long getOfficePhoneNumber() {
        return officePhoneNumber;
    }

    public void setOfficePhoneNumber(long officePhoneNumber) {
        this.officePhoneNumber = officePhoneNumber;
    }

    public long getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(long cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
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
        if (!(o instanceof CustomerTO)) {
            return false;
        }

        CustomerTO that = (CustomerTO) o;

        if (cellPhoneNumber != that.cellPhoneNumber) {
            return false;
        }
        if (homePhoneNumber != that.homePhoneNumber) {
            return false;
        }
        if (officePhoneNumber != that.officePhoneNumber) {
            return false;
        }
        if (postalCode != that.postalCode) {
            return false;
        }
        if (addressLine1 != null ? !addressLine1.equals(that.addressLine1) : that.addressLine1 != null) {
            return false;
        }
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) {
            return false;
        }
        if (customerID != null ? !customerID.equals(that.customerID) : that.customerID != null) {
            return false;
        }
        if (!emailAddress.equals(that.emailAddress)) {
            return false;
        }
        if (!firstName.equals(that.firstName)) {
            return false;
        }
        if (!lastName.equals(that.lastName)) {
            return false;
        }
        if (providenceCode != null ? !providenceCode.equals(that.providenceCode) : that.providenceCode != null) {
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
        return "CustomerTO{" +
                "customerID='" + customerID + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
