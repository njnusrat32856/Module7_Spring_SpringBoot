package com.example.travelticketbooking.repository;

import com.example.travelticketbooking.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByVehicleType(String vehicleType);

    List<Vehicle> findByOperator(String operator);
}
