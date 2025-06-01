//package com.example.travelticketbooking.models;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.Set;
//
//@Entity
//@Table(name = "schedules")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Schedule {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "route_id", nullable = false)
//    private Route route;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "vehicle_id", nullable = false)
//    private Vehicle vehicle;
//
//    @Column(name = "departure_time", nullable = false)
//    private LocalTime departureTime;
//
//    @Column(name = "arrival_time", nullable = false)
//    private LocalTime arrivalTime;
//
//    @Column(name = "available_seats", nullable = false)
//    private Integer availableSeats;
//
//    @Column(name = "price", precision = 10, scale = 2, nullable = false)
//    private BigDecimal price;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "frequency")
//    private Frequency frequency;
//
//    @Column(name = "valid_from", nullable = false)
//    private LocalDateTime validFrom;
//
//    @Column(name = "valid_until", nullable = false)
//    private LocalDateTime validUntil;
//
//    @Column(name = "days_of_operation")
//    private String daysOfOperation; // e.g., "MON,TUE,WED,THU,FRI,SAT,SUN"
//
//    @Column(name = "is_active")
//    private Boolean isActive = true;
//
//    @Column(name = "special_notes")
//    private String specialNotes;
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Booking> bookings;
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//        updatedAt = LocalDateTime.now();
//        if (availableSeats == null && vehicle != null) {
//            availableSeats = vehicle.getTotalSeats();
//        }
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = LocalDateTime.now();
//    }
//
//    public enum Frequency {
//        DAILY, WEEKLY, MONTHLY, ONCE
//    }
//}
