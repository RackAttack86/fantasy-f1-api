package com.REDACTED.fantasy_f1_api.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String email;
    private String displayName;
    private Instant createdAt;
}

// Why Separate DTOs?

// Never expose entities directly—you'd leak passwordHash
// Request/response shapes often differ from database structure
// Validation belongs on DTOs, not entities
// API versioning becomes easier