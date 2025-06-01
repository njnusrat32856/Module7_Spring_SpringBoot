package com.example.travelticketbooking.dto.request;

//import lombok.Data;

import java.time.LocalDate;

//@Data
public class SearchRequest {
    private Long fromLocationId;
    private Long toLocationId;
    private LocalDate travelDate;
    private LocalDate returnDate; // for round trip
    private String vehicleType; // BUS or TRAIN
    private Integer passengers = 1;
}
