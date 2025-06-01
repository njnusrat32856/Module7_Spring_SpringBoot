package com.example.travelticketbooking.controller;

import com.example.travelticketbooking.models.Route;
import com.example.travelticketbooking.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // Create a new route
    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        try {
            Route createdRoute = routeService.createRoute(route);
            return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        try {
            List<Route> routes = routeService.getAllRoutes();
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable("id") Long id) {
        Optional<Route> routeData = routeService.getRouteById(id);
        if (routeData.isPresent()) {
            return new ResponseEntity<>(routeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable("id") Long id, @RequestBody Route route) {
        Route updatedRoute = routeService.updateRoute(id, route);
        if (updatedRoute != null) {
            return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRoute(@PathVariable("id") Long id) {
        try {
            boolean deleted = routeService.deleteRoute(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Route>> searchRoutes(
            @RequestParam String fromLocation,
            @RequestParam String toLocation) {
        try {
            List<Route> routes = routeService.searchRoutesByLocationNames(fromLocation, toLocation);
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/available")
    public ResponseEntity<List<Route>> getAvailableRoutes(
            @RequestParam String fromLocation,
            @RequestParam String toLocation) {
        try {
            // Note: You'll need to fetch Location objects by name first
            // This is a simplified version - you might want to modify based on your Location service
            List<Route> routes = routeService.searchRoutesByLocationNames(fromLocation, toLocation);
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/price-range")
    public ResponseEntity<List<Route>> getRoutesByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        try {
            List<Route> routes = routeService.getRoutesByPriceRange(minPrice, maxPrice);
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/time-range")
    public ResponseEntity<List<Route>> getRoutesByTimeRange(
            @RequestParam String startTime,
            @RequestParam String endTime) {
        try {
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);
            List<Route> routes = routeService.getRoutesByDepartureTimeRange(start, end);
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/cheapest")
    public ResponseEntity<List<Route>> getCheapestRoutes(
            @RequestParam String fromLocation,
            @RequestParam String toLocation) {
        try {
            List<Route> routes = routeService.searchRoutesByLocationNames(fromLocation, toLocation);
            if (routes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(routes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}/seats")
    public ResponseEntity<Route> updateAvailableSeats(
            @PathVariable("id") Long id,
            @RequestParam Integer seats) {
        try {
            Route updatedRoute = routeService.updateAvailableSeats(id, seats);
            if (updatedRoute != null) {
                return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}/book")
    public ResponseEntity<Route> bookSeats(
            @PathVariable("id") Long id,
            @RequestParam Integer seatsToBook) {
        try {
            // Check if sufficient seats are available
            if (!routeService.hasSufficientSeats(id, seatsToBook)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Route updatedRoute = routeService.decreaseAvailableSeats(id, seatsToBook);
            if (updatedRoute != null) {
                return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}/check-seats")
    public ResponseEntity<Boolean> checkSeatAvailability(
            @PathVariable("id") Long id,
            @RequestParam Integer requiredSeats) {
        try {
            boolean available = routeService.hasSufficientSeats(id, requiredSeats);
            return new ResponseEntity<>(available, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
