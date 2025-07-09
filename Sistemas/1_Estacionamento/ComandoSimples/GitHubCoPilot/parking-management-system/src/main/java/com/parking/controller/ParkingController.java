package com.parking.controller;

import com.parking.model.ParkingSpot;
import com.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/add")
    public ParkingSpot addParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        return parkingService.addParkingSpot(parkingSpot);
    }

    @DeleteMapping("/remove/{id}")
    public void removeParkingSpot(@PathVariable Long id) {
        parkingService.removeParkingSpot(id);
    }

    @GetMapping("/available")
    public List<ParkingSpot> getAvailableSpots() {
        return parkingService.listAvailableSpots();
    }
}