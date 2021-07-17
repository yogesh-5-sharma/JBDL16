package com.example.vaccinebookingapp.controller;

import com.example.vaccinebookingapp.models.Location;
import com.example.vaccinebookingapp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/{locationId}")
    public Location getLocation(@PathVariable int locationId) {
        return locationService.getLocationById(locationId);
    }

    @GetMapping
    public List<Location> getLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping
    public void insertLocation(@RequestBody Location location) {
        locationService.createOrUpdate(location);
    }

    @DeleteMapping("/{locationId}")
    public void deleteLocation(@PathVariable int locationId) {
        locationService.removeLocation(locationId);
    }
}
