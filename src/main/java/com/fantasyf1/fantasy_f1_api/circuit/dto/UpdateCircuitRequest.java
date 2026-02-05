package com.fantasyf1.fantasy_f1_api.circuit.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCircuitRequest {
    
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 100, message = "Location must be at most 100 characters")
    private String location;

    @Size(max = 100, message = "Country must be at most 100 characters")
    private String country;

    @DecimalMin(value = "0.1", message = "Length must be at least 0.1 km")
    @DecimalMax(value = "99.999", message = "Length must be at most 99.999 km")
    private BigDecimal lengthKm;

    @Min(value = 1, message = "Number of laps must be at least 1")
    @Max(value = 200, message = "Number of laps must be at most 200")
    private Integer numberOfLaps;

    @Min(value = 1900, message = "First Grand Prix year must be 1900 or later")
    @Max(value = 2100, message = "First Grand Prix year must be 2100 or earlier")
    private Integer firstGrandPrixYear;
}
