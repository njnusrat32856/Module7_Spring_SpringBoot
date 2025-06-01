package com.example.travelticketbooking.services;

import com.example.travelticketbooking.models.Vehicle;
import com.example.travelticketbooking.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepo vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setVehicleName(vehicleDetails.getVehicleName());
            vehicle.setVehicleType(vehicleDetails.getVehicleType());
            vehicle.setTotalSeats(vehicleDetails.getTotalSeats());
            vehicle.setOperator(vehicleDetails.getOperator());
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    public boolean deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return vehicleRepository.findByVehicleType(vehicleType);
    }

    public List<Vehicle> getVehiclesByOperator(String operator) {
        return vehicleRepository.findByOperator(operator);
    }
}
