package com.fantasyf1.fantasy_f1_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fantasyf1.fantasy_f1_api.common.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration // Marks this as a configuration class
@EnableWebSecurity // Enables Spring Security's web security support
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF - not needed for statless JWT APIs
            // CSRF protection is for session-based apps where cookies are auto-sent
            .csrf(csrf -> csrf.disable())

            // Don't create HTTP sessions - we're using JWT tokens instead
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Define which endpoints are public vs protected
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**").permitAll() // Login endpoints - public
                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll() // Registration - public
                .anyRequest().authenticated()) // Everything else - requires JWT

            // Add our JWT filter BEFORE Spring's default authentication filter
            // This ensures JWT is checked before spring tries username/password auth
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Industry-standard password hashing
    }
}

/* BCrypt automatically handles:
- Salting (random data added to each password)
- Multiple hashing rounds (slow on purpose to prevent brute force)
- Comparing hashed passwords securely
*/
