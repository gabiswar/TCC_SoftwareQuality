package com.parking.model;

public class ParkingSpot {
    private int id;
    private boolean isOccupied;
    private String vehicleType;

    public ParkingSpot(int id, String vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }

    public int getId() {
        return id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}