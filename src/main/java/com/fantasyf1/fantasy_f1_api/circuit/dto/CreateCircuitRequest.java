package com.fantasyf1.fantasy_f1_api.circuit.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCircuitRequest {
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location must be at most 100 characters")
    private String location;

    @NotBlank
    @Size(max = 100, message = "Country must be at most 100 characters")
    private String country;

    @NotNull(message = "Length in km is required")
    @DecimalMin(value = "0.1", message = "Length must be at least 0.1 km")
    @DecimalMax(value = "99.999", message = "Length must be at most 99.999 km")
    private BigDecimal lengthKm;

    @NotNull(message = "Number of laps is required")
    @Min(value = 1, message = "Number of laps must be at least 1")
    @Max(value = 200, message = "Number of laps must be at most 200")
    private Integer numberOfLaps;

    @Min(value = 1900, message = "First Grand Prix year must be 1900 or later")
    @Max(value = 2100, message = "First Grand Prix year must be 2100 or before")
    private Integer firstGrandPrixYear;
}
