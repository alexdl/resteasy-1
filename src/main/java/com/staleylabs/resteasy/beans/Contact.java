package com.staleylabs.resteasy.beans;

import java.io.Serializable;

/**
 * Bean that represents contact information of a given entity.
 *
 * @author Sean M. Staley
 * @version 1.0 (7/30/13)
 */

public class Contact implements Serializable {

    private String addressLine1;

    private String addressLine2;

    private String cityName;

    private String providenceCode;

    private int postalCode;

    private long personalNumber;

    private long officeNumber;

    private long faxNumber;

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

    public long getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(long personalNumber) {
        this.personalNumber = personalNumber;
    }

    public long getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(long officeNumber) {
        this.officeNumber = officeNumber;
    }

    public long getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(long faxNumber) {
        this.faxNumber = faxNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }

        Contact that = (Contact) o;

        if (officeNumber != that.officeNumber) {
            return false;
        }
        if (personalNumber != that.personalNumber) {
            return false;
        }
        if (postalCode != that.postalCode) {
            return false;
        }
        if (!addressLine1.equals(that.addressLine1)) {
            return false;
        }
        if (addressLine2 != null ? !addressLine2.equals(that.addressLine2) : that.addressLine2 != null) {
            return false;
        }
        if (!cityName.equals(that.cityName)) {
            return false;
        }
        if (!providenceCode.equals(that.providenceCode)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressLine1.hashCode();
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0);
        result = 31 * result + cityName.hashCode();
        result = 31 * result + providenceCode.hashCode();
        result = 31 * result + postalCode;
        return result;
    }
}
