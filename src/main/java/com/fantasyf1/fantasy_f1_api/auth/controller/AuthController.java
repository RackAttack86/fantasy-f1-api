package com.fantasyf1.fantasy_f1_api.auth.controller;

import com.fantasyf1.fantasy_f1_api.auth.dto.AuthResponse;
import com.fantasyf1.fantasy_f1_api.auth.dto.LoginRequest;
import com.fantasyf1.fantasy_f1_api.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // Combines @Controller + @ResponseBody (returns json)
@RequestMapping("/api/v1/auth") // Base path for all methods in this controller
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/login") // POST /api/v1/auth/login
    public ResponseEntity<AuthResponse> login(
        @Valid // triggers validation annotations
        @RequestBody // parses json into object
        LoginRequest request) 
    {
        return ResponseEntity.ok(authService.login(request)); // Returns 200 OK with body
    }
}

// Flow: HTTP Request -> Controller -> Service -> Response
// -@Valid triggers the @NotBlank and @Email validations on LoginRequest
// If validation fails, Spring returns 400 Bad Request automatically
// -ResponseEntity.ok() wraps the response with HTTP 200 status