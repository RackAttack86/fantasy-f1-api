package com.REDACTED.fantasy_f1_api.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity // Marks a class as a JPA entity
@Table(name = "users") // Explicit table name
@Data // Generates getters, setters, equals, hashCode, toString
@Builder // Generates builder pattern
@NoArgsConstructor // Generates a no args constructor
@AllArgsConstructor // Generates a constructor with all arguments
public class User {
    
    @Id // Denotes primary key
    @GeneratedValue(strategy = GenerationType.UUID) // Auto-generate id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "display_name", nullable = false, length = 100)
    private String displayName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist // Lifecycle hook
    protected void onCreate()
    {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate // Lifecycle hook
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}