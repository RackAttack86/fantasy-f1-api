package com.fantasyf1.fantasy_f1_api.raceresult.dto;

// **Purpose:** DTO for partial updates. All fields are optional (nullable) - only provided fields will be updated.

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRaceResultRequest {
    private UUID teamId;
    
    @Min(value = 1, message = "Qualifying position must be at least 1")
    @Max(value = 24, message = "Qualifying position must be at most 24")
    private Integer qualifyingPosition;

    @Min(value = 1, message = "Sprint position must be at least 1")
    @Max(value = 24, message = "Sprint position must be at most 24")
    private Integer sprintPosition;

    private BigDecimal sprintPoints;

    @Min(value = 1, message = "Grid position must be at least 1")
    @Max(value = 24, message = "Grid position must be at most 24")
    private Integer gridPosition;

    @Min(value = 1, message = "Finish position must be at least 1")
    @Max(value = 24, message = "Finish position must be at most 24")
    private Integer finishPosition;

    private BigDecimal racePoints;

    private String status;

    private String dnfReason;

}
