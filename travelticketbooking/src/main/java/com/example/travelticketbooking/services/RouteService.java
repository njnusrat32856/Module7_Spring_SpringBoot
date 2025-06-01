package com.example.travelticketbooking.services;

import com.example.travelticketbooking.models.Location;
import com.example.travelticketbooking.models.Route;
import com.example.travelticketbooking.models.Vehicle;
import com.example.travelticketbooking.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepo routeRepository;


    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }


    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }


    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }


    public Route updateRoute(Long id, Route routeDetails) {
        Optional<Route> optionalRoute = routeRepository.findById(id);
        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();
            route.setFromLocation(routeDetails.getFromLocation());
            route.setToLocation(routeDetails.getToLocation());
            route.setVehicle(routeDetails.getVehicle());
            route.setDepartureTime(routeDetails.getDepartureTime());
            route.setArrivalTime(routeDetails.getArrivalTime());
            route.setPrice(routeDetails.getPrice());
            route.setAvailableSeats(routeDetails.getAvailableSeats());
            return routeRepository.save(route);
        }
        return null;
    }


    public boolean deleteRoute(Long id) {
        if (routeRepository.existsById(id)) {
            routeRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public List<Route> searchRoutes(Location fromLocation, Location toLocation) {
        return routeRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
    }


    public List<Route> searchRoutesByLocationNames(String fromLocationName, String toLocationName) {
        return routeRepository.findByLocationNames(fromLocationName, toLocationName);
    }


    public List<Route> getAvailableRoutes(Location fromLocation, Location toLocation) {
        return routeRepository.findAvailableRoutes(fromLocation, toLocation);
    }


    public List<Route> getRoutesByVehicle(Vehicle vehicle) {
        return routeRepository.findByVehicle(vehicle);
    }


    public List<Route> getRoutesByDepartureTimeRange(LocalTime startTime, LocalTime endTime) {
        return routeRepository.findByDepartureTimeBetween(startTime, endTime);
    }


    public List<Route> getRoutesByPriceRange(Double minPrice, Double maxPrice) {
        return routeRepository.findByPriceBetween(minPrice, maxPrice);
    }


    public List<Route> getCheapestRoutes(Location fromLocation, Location toLocation) {
        return routeRepository.findCheapestRoutes(fromLocation, toLocation);
    }


    public Route updateAvailableSeats(Long routeId, Integer newAvailableSeats) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();
            route.setAvailableSeats(newAvailableSeats);
            return routeRepository.save(route);
        }
        return null;
    }


    public Route decreaseAvailableSeats(Long routeId, Integer seatsToDecrease) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();
            if (route.getAvailableSeats() >= seatsToDecrease) {
                route.setAvailableSeats(route.getAvailableSeats() - seatsToDecrease);
                return routeRepository.save(route);
            }
        }
        return null;
    }


    public boolean hasSufficientSeats(Long routeId, Integer requiredSeats) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();
            return route.getAvailableSeats() >= requiredSeats;
        }
        return false;
    }
}
