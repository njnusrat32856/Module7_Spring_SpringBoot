package com.example.travelticketbooking.controller;

import com.example.travelticketbooking.models.Passenger;
import com.example.travelticketbooking.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
@CrossOrigin(origins = "*")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    // Create a new passenger
    @PostMapping
    public ResponseEntity<?> createPassenger(@RequestBody Passenger passenger) {
        try {
            Passenger createdPassenger = passengerService.createPassenger(passenger);
            return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create passenger: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        try {
            List<Passenger> passengers = passengerService.getAllPassengers();
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get passenger by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPassengerById(@PathVariable Long id) {
        try {
            Optional<Passenger> passenger = passengerService.getPassengerById(id);
            if (passenger.isPresent()) {
                return new ResponseEntity<>(passenger.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Passenger not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve passenger: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update passenger
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePassenger(@PathVariable Long id, @RequestBody Passenger passengerDetails) {
        try {
            Passenger updatedPassenger = passengerService.updatePassenger(id, passengerDetails);
            return new ResponseEntity<>(updatedPassenger, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update passenger: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete passenger
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable Long id) {
        try {
            passengerService.deletePassenger(id);
            return new ResponseEntity<>("Passenger deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete passenger: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get passengers by booking ID
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<Passenger>> getPassengersByBookingId(@PathVariable Long bookingId) {
        try {
            List<Passenger> passengers = passengerService.getPassengersByBookingId(bookingId);
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get passenger by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getPassengerByEmail(@PathVariable String email) {
        try {
            Optional<Passenger> passenger = passengerService.getPassengerByEmail(email);
            if (passenger.isPresent()) {
                return new ResponseEntity<>(passenger.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Passenger not found with email: " + email, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve passenger: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get passengers by phone number
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<List<Passenger>> getPassengersByPhoneNumber(@PathVariable String phoneNumber) {
        try {
            List<Passenger> passengers = passengerService.getPassengersByPhoneNumber(phoneNumber);
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get passenger by ID number
    @GetMapping("/id-number/{idNumber}")
    public ResponseEntity<?> getPassengerByIdNumber(@PathVariable String idNumber) {
        try {
            Optional<Passenger> passenger = passengerService.getPassengerByIdNumber(idNumber);
            if (passenger.isPresent()) {
                return new ResponseEntity<>(passenger.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Passenger not found with ID number: " + idNumber, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve passenger: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get passengers by name
    @GetMapping("/name")
    public ResponseEntity<List<Passenger>> getPassengersByName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        try {
            List<Passenger> passengers = passengerService.getPassengersByName(firstName, lastName);
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get passengers by type
    @GetMapping("/type/{passengerType}")
    public ResponseEntity<List<Passenger>> getPassengersByType(@PathVariable Passenger.PassengerType passengerType) {
        try {
            List<Passenger> passengers = passengerService.getPassengersByType(passengerType);
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get passengers with special needs
    @GetMapping("/special-needs")
    public ResponseEntity<List<Passenger>> getPassengersWithSpecialNeeds() {
        try {
            List<Passenger> passengers = passengerService.getPassengersWithSpecialNeeds();
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Count passengers by booking ID
    @GetMapping("/booking/{bookingId}/count")
    public ResponseEntity<?> countPassengersByBookingId(@PathVariable Long bookingId) {
        try {
            Long count = passengerService.countPassengersByBookingId(bookingId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to count passengers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Check seat availability
    @GetMapping("/seat/{seatNumber}/available")
    public ResponseEntity<?> checkSeatAvailability(@PathVariable String seatNumber) {
        try {
            boolean isAvailable = passengerService.isSeatAvailable(seatNumber);
            return new ResponseEntity<>(isAvailable, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to check seat availability: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get passenger by seat number
    @GetMapping("/seat/{seatNumber}")
    public ResponseEntity<?> getPassengerBySeatNumber(@PathVariable String seatNumber) {
        try {
            Optional<Passenger> passenger = passengerService.getPassengerBySeatNumber(seatNumber);
            if (passenger.isPresent()) {
                return new ResponseEntity<>(passenger.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No passenger found for seat: " + seatNumber, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve passenger: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Assign seat to passenger
    @PutMapping("/{passengerId}/seat/{seatNumber}")
    public ResponseEntity<?> assignSeat(@PathVariable Long passengerId, @PathVariable String seatNumber) {
        try {
            Passenger updatedPassenger = passengerService.assignSeat(passengerId, seatNumber);
            return new ResponseEntity<>(updatedPassenger, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to assign seat: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Assign return seat to passenger
    @PutMapping("/{passengerId}/return-seat/{returnSeatNumber}")
    public ResponseEntity<?> assignReturnSeat(@PathVariable Long passengerId, @PathVariable String returnSeatNumber) {
        try {
            Passenger updatedPassenger = passengerService.assignReturnSeat(passengerId, returnSeatNumber);
            return new ResponseEntity<>(updatedPassenger, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to assign return seat: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
