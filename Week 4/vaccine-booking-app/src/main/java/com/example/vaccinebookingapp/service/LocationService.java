package com.example.vaccinebookingapp.service;

import com.example.vaccinebookingapp.models.Location;
import com.example.vaccinebookingapp.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location getLocationById(int locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow();
    }

    public void removeLocation(int locationId) {
        locationRepository.deleteById(locationId);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public void createOrUpdate(Location location) {
        locationRepository.save(location);
    }
}
