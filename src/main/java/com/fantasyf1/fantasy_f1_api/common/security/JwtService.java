package com.REDACTED.fantasy_f1_api.common.security;

import com.REDACTED.fantasy_f1_api.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service // Registered in the application context for dependency injection
@RequiredArgsConstructor // Generates a constructor for all final fields, so Spring will inject JwtProperties automatically
public class JwtService {
    
    private final JwtProperties jwtProperties; // Config class that holds JWT secret key and expiration time (application.yml)

    /*
    Creates a jwt token. A jwt token has three parts.
    1. Header - algorithm info
    2. Payload - your claims (userId, email, expiration, etc)
    3. Signature - proves the token wasn't tampered with
    */
    public String generateToken(UUID userId, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts.builder()
                .subject(userId.toString())
                .claim("email", email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    // Token extraction methods
    // Used in authentication filter to identify who is making a request
    public UUID extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return UUID.fromString(claims.getSubject());
    }

    
    public String extractEmail(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("email", String.class);
    }

    /* Returns true only if:
    1. The token can be parsed and verified (signature is valid)
    2. Token hasn't expired
     */
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // If the token was tampered with or signed with a different key, parseSignedClaims() throws an exception
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Converts your secret string (from config) into a crytographic key for HMAC-SHA signing.
    // The same key signs tokens and verifies them later
    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

/*
THE FLOW
```
Login successful
       ↓
generateToken(userId, email)
       ↓
Return token to client
       ↓
Client sends token in header: "Authorization: Bearer eyJhbG..."
       ↓
Your auth filter calls isTokenValid(token)
       ↓
If valid, extractUserId(token) to identify the user
*/

/* 
CLAIMS
Claims are key-value pairs stored in the JWT payload. They're how you embed information
into the token itself.

Standard (Registerd) Claims. These has predefined meanings in the JWT spec:
sub -> Subject -> Who the token is about (usually user Id)
iss -> Issuer -> Who created the token (your app/service)
aud -> Audience -> Who the token is intended for (your api)
exp -> Expiration -> When the token expires
iat -> Issued At -> When the token was created
nbf -> Not Before -> Token isn't valid until this time
jti -> JWT ID -> Unique identifier for this specific token
*/

/* 
WHY PUT DATA IN CLAIMS?
**Avoid database lookups on every request.** Instead of:
```
Request comes in → Parse token → Get userId → Query DB for role → Check permissions
```
You can do:
```
Request comes in → Parse token → Role is already in the token → Check permissions */