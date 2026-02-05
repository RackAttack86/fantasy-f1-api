package com.fantasyf1.fantasy_f1_api.circuit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "circuits")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Circuit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(name = "length_km", nullable = false, precision = 5, scale = 3)
    private BigDecimal lengthKm;

    @Column(name = "number_of_laps", nullable = false)
    private Integer numberOfLaps;

    @Column(name = "first_grand_prix_year")
    private Integer firstGrandPrixYear;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
