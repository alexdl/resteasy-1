package com.staleylabs.resteasy.dto;

import com.staleylabs.resteasy.beans.Room;

import java.util.Collection;

/**
 * Object that will be delivered to the front end of the application that represents a single hotel inside of an
 * {@link OrganizationTO}.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/31/13)
 */

public class HotelTO {

    private String hotelID;

    private String organizationID;

    private String hotelName;

    private String addressLine1;

    private String addressLine2;

    private String cityName;

    private String stateCode;

    private String postalCode;

    private String countryCode;

    private long officePhoneNumber;

    private long faxPhoneNumber;

    private Collection<Room> hotelRooms;

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(String organizationID) {
        this.organizationID = organizationID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public Collection<Room> getHotelRooms() {
        return hotelRooms;
    }

    public void setHotelRooms(Collection<Room> hotelRooms) {
        this.hotelRooms = hotelRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HotelTO)) {
            return false;
        }

        HotelTO hotelTO = (HotelTO) o;

        if (addressLine1 != null ? !addressLine1.equals(hotelTO.addressLine1) : hotelTO.addressLine1 != null) {
            return false;
        }
        if (cityName != null ? !cityName.equals(hotelTO.cityName) : hotelTO.cityName != null) {
            return false;
        }
        if (countryCode != null ? !countryCode.equals(hotelTO.countryCode) : hotelTO.countryCode != null) {
            return false;
        }
        if (!hotelID.equals(hotelTO.hotelID)) {
            return false;
        }
        if (!hotelName.equals(hotelTO.hotelName)) {
            return false;
        }
        if (!organizationID.equals(hotelTO.organizationID)) {
            return false;
        }
        if (postalCode != null ? !postalCode.equals(hotelTO.postalCode) : hotelTO.postalCode != null) {
            return false;
        }
        if (stateCode != null ? !stateCode.equals(hotelTO.stateCode) : hotelTO.stateCode != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = hotelID.hashCode();
        result = 31 * result + organizationID.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + (addressLine1 != null ? addressLine1.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (stateCode != null ? stateCode.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        return result;
    }
}
