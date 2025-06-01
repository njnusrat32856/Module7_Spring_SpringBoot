package com.example.travelticketbooking.dto.request;

//import lombok.Data;

import java.time.LocalDate;
import java.util.List;

//@Data
public class BookingRequest {

    private Long userId; // To link the booking to a user
    private Long routeId; // To link the booking to a specific route
    private LocalDate travelDate;
    private LocalDate returnDate; // Can be null for one-way
    private String tripType; // e.g., "ONE_WAY" or "ROUND_TRIP"
    private String passengerName;
    private String passengerPhone;
    private String seatNumbers; // e.g., "A1,A2,B3" - consider making this a List<String> in a more complex app
    private Double totalAmount;

//    private Long routeId;
//    private LocalDate travelDate;
//    private LocalDate returnDate; // for round trip
//    private String tripType; // ONE_WAY or ROUND_TRIP
//    private String passengerName;
//    private String passengerPhone;
//    private List<String> seatNumbers;


}
