package com.example.travelticketbooking.dto;

//import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Data
public class BookingSummary {

    private Long bookingId;
    private String bookingReference;
    private String tripType;
    private String bookingStatus;
    private LocalDate travelDate;
    private String fromLocationName;
    private String toLocationName;
    private String vehicleName;
    private Double totalAmount;
    private LocalDateTime bookingDate;
}
