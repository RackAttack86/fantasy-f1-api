package com.fantasyf1.fantasy_f1_api.circuit.mapper;

import org.springframework.stereotype.Component;

import com.fantasyf1.fantasy_f1_api.circuit.dto.CircuitResponse;
import com.fantasyf1.fantasy_f1_api.circuit.entity.Circuit;

@Component
public class CircuitMapper {
    
    public CircuitResponse toResponse(Circuit circuit) {
        return CircuitResponse.builder()
                .id(circuit.getId())
                .name(circuit.getName())
                .location(circuit.getLocation())
                .country(circuit.getCountry())
                .lengthKm(circuit.getLengthKm())
                .numberOfLaps(circuit.getNumberOfLaps())
                .firstGrandPrixYear(circuit.getFirstGrandPrixYear())
                .createdAt(circuit.getCreatedAt())
                .updatedAt(circuit.getUpdatedAt())
                .build();
    }
}
