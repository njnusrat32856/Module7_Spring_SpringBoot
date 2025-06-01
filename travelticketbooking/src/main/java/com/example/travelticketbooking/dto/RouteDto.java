package com.example.travelticketbooking.dto;

//import lombok.Data;

import java.time.LocalTime;

//@Data
public class RouteDto {

    private Long routeId;
    private String fromLocationName;
    private String toLocationName;
    private String vehicleName;
    private String vehicleType;
    private String operator;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Double price;
    private Integer availableSeats;
    private Double fromLatitude;
    private Double fromLongitude;
    private Double toLatitude;
    private Double toLongitude;
}
