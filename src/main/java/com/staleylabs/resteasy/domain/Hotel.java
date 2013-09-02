package com.staleylabs.resteasy.domain;

import com.staleylabs.resteasy.beans.Contact;
import com.staleylabs.resteasy.beans.Room;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

/**
 * Entity that represents a hotel, where customers can stay. For example, one organization could host multiple
 * buildings in the corporate environment.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/24/13)
 */

@Document(collection = "hotel")
public class Hotel {

    @Id
    private String hotelId;

    private String organizationID;

    @Indexed(dropDups = false, direction = IndexDirection.ASCENDING, unique = false)
    private String hotelName;

    private Contact hotelContact;

    private Collection<Room> hotelRooms;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String id) {
        this.hotelId = hotelId;
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

    public Contact getHotelContact() {
        return hotelContact;
    }

    public void setHotelContact(Contact hotelContact) {
        this.hotelContact = hotelContact;
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
        if (!(o instanceof Hotel)) {
            return false;
        }

        Hotel hotel = (Hotel) o;

        return hotelContact.equals(hotel.hotelContact)
                && hotelName.equals(hotel.hotelName)
                && organizationID.equals(hotel.organizationID);
    }

    @Override
    public int hashCode() {
        int result = organizationID.hashCode();
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + hotelContact.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", organization=" + organizationID +
                ", id='" + hotelId + '\'' +
                '}';
    }
}
