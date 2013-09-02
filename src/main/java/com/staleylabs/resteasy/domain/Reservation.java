package com.staleylabs.resteasy.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity that represents a reservation.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/17/13)
 */

@Document(collection = "reservation")
public class Reservation implements Serializable {

    @Id
    private String id;

    private String hotelID;

    private String customerID;

    private Date checkIn;

    private Date checkOut;

    private double standardCost;

    private double additionalCost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public double getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(double standardCost) {
        this.standardCost = standardCost;
    }

    public double getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(double additionalCost) {
        this.additionalCost = additionalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservation)) {
            return false;
        }

        Reservation that = (Reservation) o;

        if (Double.compare(that.additionalCost, additionalCost) != 0) {
            return false;
        }
        if (Double.compare(that.standardCost, standardCost) != 0) {
            return false;
        }
        if (!checkIn.equals(that.checkIn)) {
            return false;
        }
        if (!checkOut.equals(that.checkOut)) {
            return false;
        }
        if (!hotelID.equals(that.hotelID)) {
            return false;
        }
        if (!id.equals(that.id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + hotelID.hashCode();
        result = 31 * result + checkIn.hashCode();
        result = 31 * result + checkOut.hashCode();
        temp = Double.doubleToLongBits(standardCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(additionalCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", hotel=" + hotelID +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
