package com.staleylabs.resteasy.beans;

/**
 * Entity that represents a given room within an organization.
 *
 * @author Sean M. Staley
 * @version 1.0 (8/24/13)
 */

public final class Room {

    private String roomName;

    private String roomDescription;

    private double roomCost;

    private int roomCapacity;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public double getRoomCost() {
        return roomCost;
    }

    public void setRoomCost(double roomCost) {
        this.roomCost = roomCost;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }

        Room room = (Room) o;

        if (roomCapacity != room.roomCapacity) {
            return false;
        }
        if (Double.compare(room.roomCost, roomCost) != 0) {
            return false;
        }
        if (!roomName.equals(room.roomName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = roomName.hashCode();
        temp = Double.doubleToLongBits(roomCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + roomCapacity;
        return result;
    }
}
