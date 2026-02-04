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

    @Transactional(readOnly = true)
    public DriverResponse getDriverById(UUID id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver", "id", id));
        
        return driverMapper.toResponse(driver);
    }

    @Transactional(readOnly = true)
    public DriverResponse getDriverByCode(String code) {
        Driver driver = driverRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Driver", "code", code));

        return driverMapper.toResponse(driver);
    }
    
    @Transactional(readOnly = true)
    public List<DriverResponse> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(driverMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DriverResponse> getDriversByTeam(UUID teamId) {
        // Verify team exists
        if (!teamRepository.existsById(teamId)) {
            throw new ResourceNotFoundException("Team", "id", teamId.toString());
        }

        return driverRepository.findByTeamId(teamId).stream() // Stream enables functional-style operations
                .map(driverMapper::toResponse) // Calls driverMapper.toResponse on each object returned from the stream
                .toList();
    }

    @Transactional
    public DriverResponse updateDriver(UUID id, UpdateDriverRequest request) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Driver", "id", id.toString()));

        // Check code uniqueness if being changed
        if (request.getCode() != null && !request.getCode().equals(driver.getCode())) {
            if (driverRepository.existsByCode(request.getCode())) {
                throw new DuplicateResourceException("Driver", "code", request.getCode());
            }
            driver.setCode(request.getCode());
        }

        // Check number uniqueness if being changed
        if (request.getNumber() != null && !request.getNumber().equals(driver.getNumber())) {
            if (driverRepository.existsByCode(request.getCode())) {
                throw new DuplicateResourceException("Driver", "number", request.getNumber().toString());
            }
            driver.setNumber(request.getNumber());
        }

        // Update team if provided
        if (request.getTeamId() != null) {
            Team team = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException("Team", "id", request.getTeamId().toString()));
            driver.setTeam(team);
        }

        if (request.getFirstName() != null) {
            driver.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null)  {
            driver.setLastName(request.getLastName());
        }

        if (request.getNationality() != null) {
            driver.setNationality(request.getNationality());
        }

        Driver updatedDriver = driverRepository.save(driver);
        return driverMapper.toResponse(updatedDriver);
    }

    @Transactional
    public void deleteDriver(UUID id) {
        if (!driverRepository.existsById(id)) {
            throw new ResourceNotFoundException("Driver", "id", id.toString());
        }
        driverRepository.deleteById(id);
    }
}
