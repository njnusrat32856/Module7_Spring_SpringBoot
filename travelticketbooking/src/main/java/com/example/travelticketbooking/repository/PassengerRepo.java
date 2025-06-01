package com.example.travelticketbooking.repository;

import com.example.travelticketbooking.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepo extends JpaRepository<Passenger, Long> {

    List<Passenger> findByBookingId(Long bookingId);


    Optional<Passenger> findByEmail(String email);


    List<Passenger> findByPhoneNumber(String phoneNumber);


    Optional<Passenger> findByIdNumber(String idNumber);


    List<Passenger> findByFirstNameAndLastName(String firstName, String lastName);


    List<Passenger> findByPassengerType(Passenger.PassengerType passengerType);


    List<Passenger> findByGender(Passenger.Gender gender);


    @Query("SELECT p FROM Passenger p WHERE p.specialNeeds IS NOT NULL AND p.specialNeeds != ''")
    List<Passenger> findPassengersWithSpecialNeeds();


    List<Passenger> findByBookingIdAndPassengerType(Long bookingId, Passenger.PassengerType passengerType);


    @Query("SELECT COUNT(p) FROM Passenger p WHERE p.booking.id = :bookingId")
    Long countPassengersByBookingId(@Param("bookingId") Long bookingId);


    Optional<Passenger> findBySeatNumber(String seatNumber);


    Optional<Passenger> findByReturnSeatNumber(String returnSeatNumber);


    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Passenger p WHERE p.seatNumber = :seatNumber OR p.returnSeatNumber = :seatNumber")
    boolean isSeatTaken(@Param("seatNumber") String seatNumber);
}
