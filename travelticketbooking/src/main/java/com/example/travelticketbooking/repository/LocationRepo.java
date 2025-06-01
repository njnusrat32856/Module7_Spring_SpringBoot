package com.example.travelticketbooking.repository;

import com.example.travelticketbooking.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {

    List<Location> findByCity(String city);


    List<Location> findByCityIgnoreCase(String city);

    Optional<Location> findByName(String name);


    Optional<Location> findByNameIgnoreCase(String name);


    List<Location> findByNameContainingIgnoreCase(String name);

    List<Location> findByCityContainingIgnoreCase(String city);

    // Custom query to find locations within a certain radius (using Haversine formula)
    @Query("SELECT l FROM Location l WHERE " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(l.latitude)) * " +
            "cos(radians(l.longitude) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(l.latitude)))) <= :radiusKm")
    List<Location> findLocationsWithinRadius(@Param("latitude") Double latitude,
                                             @Param("longitude") Double longitude,
                                             @Param("radiusKm") Double radiusKm);


}
