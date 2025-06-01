package com.example.travelticketbooking.repository;

import com.example.travelticketbooking.models.Location;
import com.example.travelticketbooking.models.Route;
import com.example.travelticketbooking.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {


    List<Route> findByFromLocationAndToLocation(Location fromLocation, Location toLocation);


    List<Route> findByFromLocation(Location fromLocation);


    List<Route> findByToLocation(Location toLocation);

    List<Route> findByVehicle(Vehicle vehicle);


    List<Route> findByAvailableSeatsGreaterThan(Integer seats);


    List<Route> findByDepartureTimeBetween(LocalTime startTime, LocalTime endTime);


    List<Route> findByPriceBetween(Double minPrice, Double maxPrice);


    @Query("SELECT r FROM Route r WHERE r.fromLocation = :fromLocation AND r.toLocation = :toLocation AND r.availableSeats > 0")
    List<Route> findAvailableRoutes(@Param("fromLocation") Location fromLocation, @Param("toLocation") Location toLocation);


    @Query("SELECT r FROM Route r WHERE r.fromLocation.name = :fromLocationName AND r.toLocation.name = :toLocationName")
    List<Route> findByLocationNames(@Param("fromLocationName") String fromLocationName, @Param("toLocationName") String toLocationName);


    @Query("SELECT r FROM Route r WHERE r.fromLocation = :fromLocation AND r.toLocation = :toLocation ORDER BY r.price ASC")
    List<Route> findCheapestRoutes(@Param("fromLocation") Location fromLocation, @Param("toLocation") Location toLocation);
}
