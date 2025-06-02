package com.example.travelticketbooking.services;

import com.example.travelticketbooking.models.Booking;
import com.example.travelticketbooking.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Optional<Booking> getBookingByReference(String bookingReference) {
        return bookingRepository.findByBookingReference(bookingReference);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByBookingStatus(status);
    }

    public List<Booking> getBookingsByTravelDate(LocalDate travelDate) {
        return bookingRepository.findByTravelDate(travelDate);
    }

    public List<Booking> getBookingsByUserAndStatus(Long userId, String status) {
        return bookingRepository.findByUserIdAndBookingStatus(userId, status);
    }

    public List<Booking> getBookingsByUserAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findByUserIdAndTravelDateBetween(userId, startDate, endDate);
    }

    public List<Booking> getBookingsByRouteAndDate(Long routeId, LocalDate travelDate) {
        return bookingRepository.findByRouteIdAndTravelDate(routeId, travelDate);
    }

    public Booking createBooking(Booking booking) {
        // generate unique booking reference
        booking.setBookingReference(generateBookingReference());
        booking.setBookingDate(LocalDateTime.now());
        booking.setBookingStatus("PENDING");
        return bookingRepository.save(booking);
    }

    public Optional<Booking> updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setTravelDate(bookingDetails.getTravelDate());
                    booking.setReturnDate(bookingDetails.getReturnDate());
                    booking.setTripType(bookingDetails.getTripType());
                    booking.setPassengerName(bookingDetails.getPassengerName());
                    booking.setPassengerPhone(bookingDetails.getPassengerPhone());
                    booking.setSeatNumbers(bookingDetails.getSeatNumbers());
                    booking.setTotalAmount(bookingDetails.getTotalAmount());
                    return bookingRepository.save(booking);
                });
    }

    public Optional<Booking> updateBookingStatus(Long id, String status) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setBookingStatus(status);
                    return bookingRepository.save(booking);
                });
    }

    public Optional<Booking> confirmBooking(Long id) {
        return updateBookingStatus(id, "CONFIRMED");
    }

    public Optional<Booking> cancelBooking(Long id) {
        return updateBookingStatus(id, "CANCELLED");
    }

    public boolean deleteBooking(Long id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    bookingRepository.delete(booking);
                    return true;
                }).orElse(false);
    }

    private String generateBookingReference() {
        return "BK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }


}
