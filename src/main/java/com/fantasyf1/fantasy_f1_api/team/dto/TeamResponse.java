package com.fantasyf1.fantasy_f1_api.team.dto;

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
public class TeamResponse {
    
    private UUID id;
    private String name;
    private String fullName;
    private String base;
    private String teamPrincipal;
    private Instant createdAt;
    private Instant updatedAt;
}
