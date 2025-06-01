package com.example.travelticketbooking.services;

import com.example.travelticketbooking.models.UserModel;
import com.example.travelticketbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserModel> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserModel> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public UserModel createUser(UserModel user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }

        // Encrypt password before saving
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role if not provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("CUSTOMER");
        }

        return userRepository.save(user);
    }

    public UserModel updateUser(Long id, UserModel userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(userDetails.getEmail());
                    user.setFirstName(userDetails.getFirstName());
                    user.setLastName(userDetails.getLastName());
                    user.setPhoneNumber(userDetails.getPhoneNumber());
                    user.setRole(userDetails.getRole());

                    // Only update password if provided
//                    if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
//                        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
//                    }

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<UserModel> searchUsersByName(String name) {
        List<UserModel> byFirstName = userRepository.findByFirstNameContainingIgnoreCase(name);
        List<UserModel> byLastName = userRepository.findByLastNameContainingIgnoreCase(name);

        // Combine results and remove duplicates
        byFirstName.addAll(byLastName);
        return byFirstName.stream().distinct().toList();
    }

//    public boolean validatePassword(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
}
