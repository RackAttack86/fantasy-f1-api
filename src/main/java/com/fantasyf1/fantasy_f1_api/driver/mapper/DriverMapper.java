package com.fantasyf1.fantasy_f1_api.driver.mapper;

import com.fantasyf1.fantasy_f1_api.driver.dto.DriverResponse;
import com.fantasyf1.fantasy_f1_api.driver.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    
    public DriverResponse toResponse(Driver driver) {
        return DriverResponse.builder()
                .id(driver.getId())
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .code(driver.getCode())
                .number(driver.getNumber())
                .teamId(driver.getTeam() != null ? driver.getTeam().getId() : null)
                .teamName(driver.getTeam() != null ? driver.getTeam().getName(): null)
                .nationality(driver.getNationality())
                .createdAt(driver.getCreatedAt())
                .updatedAt(driver.getUpdatedAt())
                .build();
    }
}
