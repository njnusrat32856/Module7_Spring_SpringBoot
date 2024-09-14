package com.nusrat.PracticeBank.service;

import com.nusrat.PracticeBank.entity.AuthenticationResponse;
import com.nusrat.PracticeBank.entity.Token;
import com.nusrat.PracticeBank.entity.User;
import com.nusrat.PracticeBank.jwt.JwtService;
import com.nusrat.PracticeBank.repository.TokenRepository;
import com.nusrat.PracticeBank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    private void saveUserToken(String jwt, User user) {

        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);

        tokenRepository.save(token);

    }
    private void revokeAllTokenByUser(User user) {

        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }
        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });
        tokenRepository.saveAll(validTokens);
    }
    public AuthenticationResponse register(User request) {
        if (userRepository.findByEmail(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setDob(request.getDob());
        user.setPhone(request.getPhone());
        user = userRepository.save(request);

        String jwt = jwtService.generateToken(request);
        saveUserToken(jwt, request);

        return new AuthenticationResponse(jwt, "User registration was successful");
    }
    public AuthenticationResponse authenticate(User request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getUsername())
                .orElseThrow();

        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);

        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt,"User login was successful");
    }

}
