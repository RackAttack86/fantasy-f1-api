package com.fantasyf1.fantasy_f1_api.team.mapper;

import com.fantasyf1.fantasy_f1_api.team.dto.TeamResponse;
import com.fantasyf1.fantasy_f1_api.team.entity.Team;
import org.springframework.stereotype.Component;

@Component // Makes this injectable via Spring DI
public class TeamMapper {
    
    public TeamResponse toResponse(Team team) {
        return TeamResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .fullName(team.getFullName())
                .base(team.getBase())
                .teamPrincipal(team.getTeamPrincipal())
                .createdAt(team.getCreatedAt())
                .updatedAt(team.getUpdatedAt())
                .build();
    }
}

// Single responsibility: converts Entity -> Response DTO
// Uses builder pattern for clean construction