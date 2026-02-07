package com.fantasyf1.fantasy_f1_api.raceresult.dto;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaceResult {
    
    private UUID id;
    private UUID raceId;
    private String raceName;
    private Integer season;
    private Integer round;

    private UUID driverId;
    private String driverCode;
    private String driverName;

    private UUID teamId;
    private String teamName;

    private Integer qualifyingPosition;
    private Integer sprintPosition;
    private BigDecimal sprintPoints;
    private Integer gridPosition;
    private Integer finishPosition;
    private BigDecimal racePoints;
    private String status;
    private String dnfReason;

    private Instant createdAt;
    private Instant updatedAt;
}
