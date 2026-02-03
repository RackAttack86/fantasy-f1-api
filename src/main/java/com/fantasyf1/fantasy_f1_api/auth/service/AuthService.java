package com.fantasyf1.fantasy_f1_api.auth.service;

import com.fantasyf1.fantasy_f1_api.auth.dto.AuthResponse;
import com.fantasyf1.fantasy_f1_api.auth.dto.LoginRequest;
import com.fantasyf1.fantasy_f1_api.common.exception.BadCredentialsException;
import com.fantasyf1.fantasy_f1_api.common.security.JwtService;
import com.fantasyf1.fantasy_f1_api.config.JwtProperties;
import com.fantasyf1.fantasy_f1_api.user.entity.User;
import com.fantasyf1.fantasy_f1_api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Marks this as a Spring-managed service bean
@RequiredArgsConstructor // Lombok generates constructor for all final fields
public class AuthService {
    
    private final UserRepository userRepository; // Database access
    private final PasswordEncoder passwordEncoder; // BCrypt encoder
    private final JwtService jwtService; // Token generation
    private final JwtProperties jwtProperties; // Config(expiration time)

    // The login method
    public AuthResponse login(LoginRequest request) {
        // Step 1: Find user by email, throw exception if not found
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
        
        // Step 2: Compare plaintext password with stored BCrypt hash
        // passwordEncoder.matches() handles the BCrypt comparison
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        
        // Step 3: Generate JWT token with user's ID and email embedded
        String token = jwtService.generateToken(user.getId(), user.getEmail());

        // Step 4: Return response with token details
        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer") // Standard token type for HTTP Authorization header
                .expiresIn(jwtProperties.getExpiration()) // Tell client when token expires
                .build();
    }
}
