package com.REDACTED.fantasy_f1_api.user.service;

import com.REDACTED.fantasy_f1_api.user.dto.CreateUserRequest;
import com.REDACTED.fantasy_f1_api.user.dto.UserResponse;
import com.REDACTED.fantasy_f1_api.user.entity.User;
import com.REDACTED.fantasy_f1_api.user.mapper.UserMapper;
import com.REDACTED.fantasy_f1_api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor // Generates a constructor with all final fields—this is constructor injection
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .displayName(request.getDisplayName())
                .build();

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Transactional(readOnly = true) // Optimizes read queries
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found."));
        return userMapper.toResponse(user);
    }
}
