package com.example.travelticketbooking.services;

import com.example.travelticketbooking.models.Location;
import com.example.travelticketbooking.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepository;

    // Get all locations
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // Get location by ID
    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    // Get location by name
    public Optional<Location> getLocationByName(String name) {
        return locationRepository.findByNameIgnoreCase(name);
    }

    // Get locations by city
    public List<Location> getLocationsByCity(String city) {
        return locationRepository.findByCityIgnoreCase(city);
    }

    // Search locations by name (partial match)
    public List<Location> searchLocationsByName(String name) {
        return locationRepository.findByNameContainingIgnoreCase(name);
    }

    // Search locations by city (partial match)
    public List<Location> searchLocationsByCity(String city) {
        return locationRepository.findByCityContainingIgnoreCase(city);
    }

    // Get locations within radius
    public List<Location> getLocationsWithinRadius(Double latitude, Double longitude, Double radiusKm) {
        return locationRepository.findLocationsWithinRadius(latitude, longitude, radiusKm);
    }

    // Create a new location
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    // Update an existing location
    public Location updateLocation(Long id, Location locationDetails) {
        Optional<Location> optionalLocation = locationRepository.findById(id);

        if (optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            location.setName(locationDetails.getName());
            location.setCity(locationDetails.getCity());
            location.setLatitude(locationDetails.getLatitude());
            location.setLongitude(locationDetails.getLongitude());
            return locationRepository.save(location);
        } else {
            throw new RuntimeException("Location not found with id: " + id);
        }
    }

    // Delete a location
    public void deleteLocation(Long id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Location not found with id: " + id);
        }
    }

    // Check if location exists
    public boolean locationExists(Long id) {
        return locationRepository.existsById(id);
    }

    // Calculate distance between two locations (Haversine formula)
    public double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        final int R = 6371; // Radius of the earth in km

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Distance in km

        return distance;
    }
}
