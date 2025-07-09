package com.parking.service;

import com.parking.model.ParkingSpot;
import java.util.ArrayList;
import java.util.List;

public class ParkingService {
    private List<ParkingSpot> parkingSpots;

    public ParkingService() {
        this.parkingSpots = new ArrayList<>();
    }

    public void parkVehicle(ParkingSpot spot) {
        if (!spot.isOccupied()) {
            spot.setOccupied(true);
            parkingSpots.add(spot);
        } else {
            throw new IllegalStateException("Parking spot is already occupied.");
        }
    }

    public void unparkVehicle(ParkingSpot spot) {
        if (spot.isOccupied()) {
            spot.setOccupied(false);
            parkingSpots.remove(spot);
        } else {
            throw new IllegalStateException("Parking spot is already vacant.");
        }
    }

    public List<ParkingSpot> listAvailableSpots() {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isOccupied()) {
                availableSpots.add(spot);
            }
        }
        return availableSpots;
    }
}