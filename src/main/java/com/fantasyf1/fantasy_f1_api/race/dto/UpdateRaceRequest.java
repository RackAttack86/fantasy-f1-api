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
public class UpdateRaceRequest {
    
    @Size(max = 255, message = "Name must be at most 255 characters")
    private String name;

    @Min(value = 1950, message = "Season must be 1950 or later")
    @Max(value = 2100, message = "Season must be 2100 or earlier")
    private Integer season;

    @Min(value = 1, message = "Round must be at least 1")
    @Max(value = 30, message = "Round must be at most 30")
    private Integer round;

    private UUID circuitId;

    private LocalDate raceDate;

    private LocalDate qualifyingDate;

    private LocalDate sprintDate;
}
