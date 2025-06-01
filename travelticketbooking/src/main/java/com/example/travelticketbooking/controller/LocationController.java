package com.example.travelticketbooking.controller;

import com.example.travelticketbooking.models.Location;
import com.example.travelticketbooking.services.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // Get all locations
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    // Get location by ID
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> location = locationService.getLocationById(id);
        return location.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get locations by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Location>> getLocationsByCity(@PathVariable String city) {
        List<Location> locations = locationService.getLocationsByCity(city);
        return ResponseEntity.ok(locations);
    }

    // Search locations by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Location>> searchLocationsByName(@RequestParam String name) {
        List<Location> locations = locationService.searchLocationsByName(name);
        return ResponseEntity.ok(locations);
    }

    // Search locations by city
    @GetMapping("/search/city")
    public ResponseEntity<List<Location>> searchLocationsByCity(@RequestParam String city) {
        List<Location> locations = locationService.searchLocationsByCity(city);
        return ResponseEntity.ok(locations);
    }

    // Get locations within radius
    @GetMapping("/nearby")
    public ResponseEntity<List<Location>> getLocationsWithinRadius(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(defaultValue = "10.0") Double radius) {
        List<Location> locations = locationService.getLocationsWithinRadius(latitude, longitude, radius);
        return ResponseEntity.ok(locations);
    }

    // Create a new location
    @PostMapping
    public ResponseEntity<Location> createLocation(@Valid @RequestBody Location location) {
        try {
            Location createdLocation = locationService.createLocation(location);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Update an existing location
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id,
                                                   @Valid @RequestBody Location locationDetails) {
        try {
            Location updatedLocation = locationService.updateLocation(id, locationDetails);
            return ResponseEntity.ok(updatedLocation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Partially update a location
    @PatchMapping("/{id}")
    public ResponseEntity<Location> partialUpdateLocation(@PathVariable Long id,
                                                          @RequestBody Location locationDetails) {
        try {
            Optional<Location> optionalLocation = locationService.getLocationById(id);
            if (optionalLocation.isPresent()) {
                Location existingLocation = optionalLocation.get();

                // Update only non-null fields
                if (locationDetails.getName() != null) {
                    existingLocation.setName(locationDetails.getName());
                }
                if (locationDetails.getCity() != null) {
                    existingLocation.setCity(locationDetails.getCity());
                }
                if (locationDetails.getLatitude() != null) {
                    existingLocation.setLatitude(locationDetails.getLatitude());
                }
                if (locationDetails.getLongitude() != null) {
                    existingLocation.setLongitude(locationDetails.getLongitude());
                }

                Location updatedLocation = locationService.createLocation(existingLocation);
                return ResponseEntity.ok(updatedLocation);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete a location
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        try {
            locationService.deleteLocation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Check if location exists
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> locationExists(@PathVariable Long id) {
        boolean exists = locationService.locationExists(id);
        return ResponseEntity.ok(exists);
    }

    // Calculate distance between two locations
    @GetMapping("/distance")
    public ResponseEntity<Double> calculateDistance(
            @RequestParam Double lat1,
            @RequestParam Double lon1,
            @RequestParam Double lat2,
            @RequestParam Double lon2) {
        double distance = locationService.calculateDistance(lat1, lon1, lat2, lon2);
        return ResponseEntity.ok(distance);
    }
}
