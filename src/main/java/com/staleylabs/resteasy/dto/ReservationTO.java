package com.staleylabs.resteasy.dto;

import java.util.Date;

/**
 * Reservation object that will be used by the front end of the application.
 *
 * @author Sean M. Staley
 * @version 1.0 (9/2/13)
 */

public class ReservationTO {

    private String reservationID;

    private String organizationID;

    private String hotelID;

    private String roomName;

    private CustomerTO customer;

    private Date customerCheckIn;

    private Date customerCheckOut;

    private double baseCost;

    private double additionalCost;

    private double taxCost;

    private int customerStayCount;

    private String additionalNotes;

    private String reservationStatus;

    private long creationDate;

    private long lastModifiedDate;

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(String organizationID) {
        this.organizationID = organizationID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getCustomerCheckIn() {
        return customerCheckIn;
    }

    public void setCustomerCheckIn(Date customerCheckIn) {
        this.customerCheckIn = customerCheckIn;
    }

    public Date getCustomerCheckOut() {
        return customerCheckOut;
    }

    public void setCustomerCheckOut(Date customerCheckOut) {
        this.customerCheckOut = customerCheckOut;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(double baseCost) {
        this.baseCost = baseCost;
    }

    public double getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(double additionalCost) {
        this.additionalCost = additionalCost;
    }

    public double getTaxCost() {
        return taxCost;
    }

    public void setTaxCost(double taxCost) {
        this.taxCost = taxCost;
    }

    public int getCustomerStayCount() {
        return customerStayCount;
    }

    public void setCustomerStayCount(int customerStayCount) {
        this.customerStayCount = customerStayCount;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public CustomerTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTO customer) {
        this.customer = customer;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReservationTO)) {
            return false;
        }

        ReservationTO that = (ReservationTO) o;

        if (customerStayCount != that.customerStayCount) {
            return false;
        }
        if (!customerCheckIn.equals(that.customerCheckIn)) {
            return false;
        }
        if (!customerCheckOut.equals(that.customerCheckOut)) {
            return false;
        }
        if (!customer.equals(that.customer)) {
            return false;
        }
        if (!hotelID.equals(that.hotelID)) {
            return false;
        }
        if (!organizationID.equals(that.organizationID)) {
            return false;
        }
        if (reservationID != null ? !reservationID.equals(that.reservationID) : that.reservationID != null) {
            return false;
        }
        if (!roomName.equals(that.roomName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservationID != null ? reservationID.hashCode() : 0;
        result = 31 * result + organizationID.hashCode();
        result = 31 * result + hotelID.hashCode();
        result = 31 * result + roomName.hashCode();
        result = 31 * result + customerCheckIn.hashCode();
        result = 31 * result + customerCheckOut.hashCode();
        result = 31 * result + customerStayCount;
        return result;
    }

    @Override
    public String toString() {
        return "ReservationTO{" +
                "reservationID='" + reservationID + '\'' +
                ", organizationID='" + organizationID + '\'' +
                ", hotelID='" + hotelID + '\'' +
                ", roomName='" + roomName + '\'' +
                ", customerEmailAddress='" + customer + '\'' +
                ", customerCheckIn=" + customerCheckIn +
                ", customerCheckOut=" + customerCheckOut +
                ", baseCost=" + baseCost +
                ", additionalCost=" + additionalCost +
                ", taxCost=" + taxCost +
                ", customerStayCount=" + customerStayCount +
                '}';
    }
}
