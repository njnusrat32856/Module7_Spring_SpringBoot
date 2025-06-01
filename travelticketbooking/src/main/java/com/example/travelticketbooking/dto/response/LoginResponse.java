package com.example.travelticketbooking.dto.response;

//import lombok.Data;

//@Data
public class LoginResponse {

    private String token;
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String role;

    public LoginResponse(String token, Long userId, String email, String firstName, String lastName, String role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
