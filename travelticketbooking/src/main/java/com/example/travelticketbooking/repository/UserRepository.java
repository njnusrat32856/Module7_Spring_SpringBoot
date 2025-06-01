package com.example.travelticketbooking.repository;

import com.example.travelticketbooking.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);

    List<UserModel> findByRole(String role);

    List<UserModel> findByFirstNameContainingIgnoreCase(String firstName);

    List<UserModel> findByLastNameContainingIgnoreCase(String lastName);

    Optional<UserModel> findByPhoneNumber(String phoneNumber);
}
