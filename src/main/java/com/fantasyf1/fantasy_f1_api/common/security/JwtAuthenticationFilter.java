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
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // No token provided - continue without authentication
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);

        if (jwtService.isTokenValid(token)) {
            UUID userId = jwtService.extractUserId(token);
            String email = jwtService.extractEmail(token);

            // Create authentication token with userId as principal
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userId, // principal - the user's ID
                email, // credentials - the user's email
                Collections.emptyList() // authorities (empty for now)
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
