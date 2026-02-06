package com.fantasyf1.fantasy_f1_api.race.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaceResponse {
    private UUID id;
    private String name;
    private Integer season;
    private Integer round;
    private UUID circuitId;
    private String circuitName;
    private String circuitCountry;
    private LocalDate raceDate;
    private LocalDate qualifyingDate;
    private LocalDate sprintDate;
    private Instant createdAt;
    private Instant updatedAt;
}
