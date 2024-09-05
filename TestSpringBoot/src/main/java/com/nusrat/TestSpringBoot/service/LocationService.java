package com.nusrat.TestSpringBoot.service;

import com.nusrat.TestSpringBoot.entity.Location;
import com.nusrat.TestSpringBoot.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public void saveLocation(Location l) {
        locationRepository.save(l);
    }

    public void deleteLocation(int id) {
        locationRepository.deleteById(id);
    }
    public Location findById(int id) {
        return locationRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Location not found by this id")
                );
    }
}
