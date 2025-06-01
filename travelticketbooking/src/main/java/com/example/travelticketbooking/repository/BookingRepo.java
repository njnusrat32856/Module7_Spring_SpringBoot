package com.example.travelticketbooking.repository;

import com.example.travelticketbooking.models.Booking;
import com.example.travelticketbooking.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingReference(String bookingReference);

    List<Booking> findByUserId(Long userId);

    List<Booking> findByBookingStatus(String bookingStatus);

    List<Booking> findByTravelDate(LocalDate travelDate);

    List<Booking> findByUserIdAndBookingStatus(Long userId, String bookingStatus);

    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId AND b.travelDate BETWEEN :startDate AND :endDate")
    List<Booking> findByUserIdAndTravelDateBetween(@Param("userId") Long userId,
                                                   @Param("startDate") LocalDate startDate,
                                                   @Param("endDate") LocalDate endDate);

    @Query("SELECT b FROM Booking b WHERE b.route.id = :routeId AND b.travelDate = :travelDate")
    List<Booking> findByRouteIdAndTravelDate(@Param("routeId") Long routeId,
                                             @Param("travelDate") LocalDate travelDate);
}
