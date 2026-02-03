package com.fantasyf1.fantasy_f1_api.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponse {
    
    private UUID id;
    private String firstName;
    private String lastName;
    private String code;
    private Integer number;
    private UUID teamId;
    private String teamName; // Included to avoid clients needing to make a separate API call to get the tema name.
    private String nationality;
    private Instant createdAt;
    private Instant updatedAt;
}
