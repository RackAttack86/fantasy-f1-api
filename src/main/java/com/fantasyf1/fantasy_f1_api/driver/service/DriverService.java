package com.fantasyf1.fantasy_f1_api.driver.service;

import com.fantasyf1.fantasy_f1_api.common.exception.DuplicateResourceException;
import com.fantasyf1.fantasy_f1_api.common.exception.ResourceNotFoundException;
import com.fantasyf1.fantasy_f1_api.driver.dto.CreateDriverRequest;
import com.fantasyf1.fantasy_f1_api.driver.dto.DriverResponse;
import com.fantasyf1.fantasy_f1_api.driver.dto.UpdateDriverRequest;
import com.fantasyf1.fantasy_f1_api.driver.entity.Driver;
import com.fantasyf1.fantasy_f1_api.driver.mapper.DriverMapper;
import com.fantasyf1.fantasy_f1_api.driver.repository.DriverRepository;
import com.fantasyf1.fantasy_f1_api.team.entity.Team;
import com.fantasyf1.fantasy_f1_api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverService {
    
    private final DriverRepository driverRepository;
    private final TeamRepository teamRepository;
    private final DriverMapper driverMapper;

    @Transactional
    public DriverResponse createDriver(CreateDriverRequest request) {
        // Check for duplicate code
        if (driverRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException("Driver", "code", request.getCode());
        }

        // Check for duplicate number
        if (driverRepository.existsByNumber(request.getNumber())) {
            throw new DuplicateResourceException("Driver", "number", request.getNumber().toString());
        }

        // Validate team exists if provided
        Team team = null;
        if (request.getTeamId() != null) {
            team = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team", "id", 
                    request.getTeamId().toString()));
        }

        Driver driver = Driver.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .code(request.getCode())
                .number(request.getNumber())
                .team(team)
                .nationality(request.getNationality())
                .build();

        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toResponse(savedDriver);
    }
}
