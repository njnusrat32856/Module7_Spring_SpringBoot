package com.example.travelticketbooking.dto.response;

import com.example.travelticketbooking.dto.RouteDetails;
//import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//@Data
public class BookingResponse {

    private Long bookingId;
    private String bookingReference;
    private String tripType;
    private String bookingStatus;
    private Double totalAmount;
    private LocalDateTime bookingDate;
    private RouteDetails routeDetails;
    private String passengerName;
    private String passengerPhone;
    private List<String> seatNumbers;
}
