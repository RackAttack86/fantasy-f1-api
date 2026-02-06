package com.fantasyf1.fantasy_f1_api.race.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRaceRequest {
    
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be at most 255 characters")
    private String name;

    @NotNull(message = "Season is required")
    @Min(value = 1950, message = "Season must be 1950 or later")
    @Max(value = 2100, message = "Season must be 2100 or earlier")
    private Integer season;

    @NotNull(message = "Round is required")
    @Min(value = 1, message = "Round must be at least 1")
    @Max(value = 30, message = "Round must be at most 30")
    private Integer round;

    @NotNull(message = "Circuit ID is required")
    private UUID circuitId;

    @NotNull(message = "Race date is required")
    private LocalDate raceDate;

    private LocalDate qualifyingDate;

    private LocalDate sprintDate;
}
