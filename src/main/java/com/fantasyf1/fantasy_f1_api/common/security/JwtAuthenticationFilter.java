package com.fantasyf1.fantasy_f1_api.common.security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component // Spring-managed bean (auto-detected)
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter // OncePerRequestFilter: Guarantees this filte runs exactly once per request (some filters can accidentally run multiple times)
{

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request, // The incoming http request
            @Nonnull HttpServletResponse response, // The outgoing http response
            @Nonnull FilterChain filterChain // The chain of filters to continue
    ) throws ServletException, IOException {

        // Step 1: Get the authorization header
        final String authHeader = request.getHeader("Authorization");

        // Step 2: If no token or wrong format, continue without authenticating
        // This lets public endpoints work (login, register)
        // No token provided - continue without authentication
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Step 3: Extract token (remove "Bearer " prefix)
        final String token = authHeader.substring(7);

        // Step 4: Validate and set authentication
        if (jwtService.isTokenValid(token)) {
            UUID userId = jwtService.extractUserId(token);
            String email = jwtService.extractEmail(token);

            // Step 5: Create Spring Security authentication object
            // Create authentication token with userId as principal
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userId, // principal - the user's ID
                email, // credentials - the user's email
                Collections.emptyList() // authorities (empty for now)
            );

            // Step 6: Store in SecurityContext - this is how Spring knows the user is authenticated
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        // Step 7: Continue to next filter (eventually reaches your controller)
        filterChain.doFilter(request, response);
    }
}

// Key Concept: SecurityContextHolder is thread-local storage. 
// Once you set authentication there, any code in that request can check if the user is authenticated.