package com.example.travelticketbooking.dto;

//import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

//@Data
public class RouteDetails {

    private String fromLocationName;
    private String toLocationName;
    private String vehicleName;
    private String vehicleType;
    private String operator;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalDate travelDate;
    private LocalDate returnDate;
}
