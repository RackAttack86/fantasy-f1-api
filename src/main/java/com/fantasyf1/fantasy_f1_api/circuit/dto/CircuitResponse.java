package com.fantasyf1.fantasy_f1_api.circuit.dto;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CircuitResponse {
    
    private UUID id;
    private String name;
    private String location;
    private String country;
    private BigDecimal lengthKm;
    private Integer numberOfLaps;
    private Integer firstGrandPrixYear;
    private Instant createdAt;
    private Instant updatedAt;
}
