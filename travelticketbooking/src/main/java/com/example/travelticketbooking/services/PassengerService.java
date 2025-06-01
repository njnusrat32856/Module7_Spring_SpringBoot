package com.example.travelticketbooking.services;

import com.example.travelticketbooking.models.Passenger;
import com.example.travelticketbooking.repository.PassengerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PassengerService {

    @Autowired
    private PassengerRepo passengerRepository;

    @Autowired
    public PassengerService(PassengerRepo passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger createPassenger(Passenger passenger) {
        validatePassenger(passenger);
        return passengerRepository.save(passenger);
    }


    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }


    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }


    public Passenger updatePassenger(Long id, Passenger passengerDetails) {
        return passengerRepository.findById(id)
                .map(passenger -> {
                    updatePassengerFields(passenger, passengerDetails);
                    return passengerRepository.save(passenger);
                })
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));
    }


    public void deletePassenger(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new RuntimeException("Passenger not found with id: " + id);
        }
        passengerRepository.deleteById(id);
    }


    public List<Passenger> getPassengersByBookingId(Long bookingId) {
        return passengerRepository.findByBookingId(bookingId);
    }


    public Optional<Passenger> getPassengerByEmail(String email) {
        return passengerRepository.findByEmail(email);
    }


    public List<Passenger> getPassengersByPhoneNumber(String phoneNumber) {
        return passengerRepository.findByPhoneNumber(phoneNumber);
    }


    public Optional<Passenger> getPassengerByIdNumber(String idNumber) {
        return passengerRepository.findByIdNumber(idNumber);
    }


    public List<Passenger> getPassengersByName(String firstName, String lastName) {
        return passengerRepository.findByFirstNameAndLastName(firstName, lastName);
    }


    public List<Passenger> getPassengersByType(Passenger.PassengerType passengerType) {
        return passengerRepository.findByPassengerType(passengerType);
    }


    public List<Passenger> getPassengersWithSpecialNeeds() {
        return passengerRepository.findPassengersWithSpecialNeeds();
    }


    public Long countPassengersByBookingId(Long bookingId) {
        return passengerRepository.countPassengersByBookingId(bookingId);
    }


    public boolean isSeatAvailable(String seatNumber) {
        return !passengerRepository.isSeatTaken(seatNumber);
    }


    public Optional<Passenger> getPassengerBySeatNumber(String seatNumber) {
        return passengerRepository.findBySeatNumber(seatNumber);
    }


    public Passenger assignSeat(Long passengerId, String seatNumber) {
        if (!isSeatAvailable(seatNumber)) {
            throw new RuntimeException("Seat " + seatNumber + " is already taken");
        }

        return passengerRepository.findById(passengerId)
                .map(passenger -> {
                    passenger.setSeatNumber(seatNumber);
                    return passengerRepository.save(passenger);
                })
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + passengerId));
    }


    public Passenger assignReturnSeat(Long passengerId, String returnSeatNumber) {
        if (!isSeatAvailable(returnSeatNumber)) {
            throw new RuntimeException("Return seat " + returnSeatNumber + " is already taken");
        }

        return passengerRepository.findById(passengerId)
                .map(passenger -> {
                    passenger.setReturnSeatNumber(returnSeatNumber);
                    return passengerRepository.save(passenger);
                })
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + passengerId));
    }


    private void validatePassenger(Passenger passenger) {
        if (passenger.getFirstName() == null || passenger.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (passenger.getLastName() == null || passenger.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (passenger.getGender() == null) {
            throw new IllegalArgumentException("Gender is required");
        }
        if (passenger.getBooking() == null) {
            throw new IllegalArgumentException("Booking is required");
        }
    }

    private void updatePassengerFields(Passenger existingPassenger, Passenger newDetails) {
        if (newDetails.getFirstName() != null) {
            existingPassenger.setFirstName(newDetails.getFirstName());
        }
        if (newDetails.getLastName() != null) {
            existingPassenger.setLastName(newDetails.getLastName());
        }
        if (newDetails.getDateOfBirth() != null) {
            existingPassenger.setDateOfBirth(newDetails.getDateOfBirth());
        }
        if (newDetails.getGender() != null) {
            existingPassenger.setGender(newDetails.getGender());
        }
        if (newDetails.getPhoneNumber() != null) {
            existingPassenger.setPhoneNumber(newDetails.getPhoneNumber());
        }
        if (newDetails.getEmail() != null) {
            existingPassenger.setEmail(newDetails.getEmail());
        }
        if (newDetails.getIdNumber() != null) {
            existingPassenger.setIdNumber(newDetails.getIdNumber());
        }
        if (newDetails.getIdType() != null) {
            existingPassenger.setIdType(newDetails.getIdType());
        }
        if (newDetails.getSeatNumber() != null) {
            existingPassenger.setSeatNumber(newDetails.getSeatNumber());
        }
        if (newDetails.getReturnSeatNumber() != null) {
            existingPassenger.setReturnSeatNumber(newDetails.getReturnSeatNumber());
        }
        if (newDetails.getPassengerType() != null) {
            existingPassenger.setPassengerType(newDetails.getPassengerType());
        }
        if (newDetails.getSpecialNeeds() != null) {
            existingPassenger.setSpecialNeeds(newDetails.getSpecialNeeds());
        }
    }
}
