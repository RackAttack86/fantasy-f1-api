package com.fantasyf1.fantasy_f1_api.race.mapper;

import com.fantasyf1.fantasy_f1_api.race.dto.RaceResponse;
import com.fantasyf1.fantasy_f1_api.race.entity.Race;
import org.springframework.stereotype.Component;

@Component
public class RaceMapper {
    
    public RaceResponse toResponse(Race race) {
        return RaceResponse.builder()
                .id(race.getId())
                .name(race.getName())
                .season(race.getSeason())
                .round(race.getRound())
                .circuitId(race.getCircuit() != null ? race.getCircuit().getId() : null)
                .circuitName(race.getCircuit() != null ? race.getCircuit().getName() : null)
                .circuitCountry(race.getCircuit() != null ? race.getCircuit().getCountry() : null)
                .raceDate(race.getRaceDate())
                .qualifyingDate(race.getQualifyingDate())
                .sprintDate(race.getSprintDate())
                .createdAt(race.getCreatedAt())
                .updatedAt(race.getUpdatedAt())
                .build();
    }
}
