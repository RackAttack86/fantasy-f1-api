package com.fantasyf1.fantasy_f1_api.driver.controller;

import com.fantasyf1.fantasy_f1_api.driver.dto.CreateDriverRequest;
import com.fantasyf1.fantasy_f1_api.driver.dto.DriverResponse;
import com.fantasyf1.fantasy_f1_api.driver.dto.UpdateDriverRequest;
import com.fantasyf1.fantasy_f1_api.driver.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {
    
    private final DriverService driverService;

    @PostMapping()
    public ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody CreateDriverRequest request) {
        DriverResponse response = driverService.createDriver(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getDriver(@PathVariable UUID id) {
        DriverResponse response = driverService.getDriverById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<DriverResponse> getDriverByCode(@PathVariable String code) {
        DriverResponse response = driverService.getDriverByCode(code);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<DriverResponse>> getAllDrivers(
            @RequestParam(required = false) UUID teamId) {
        List<DriverResponse> response;
        if (teamId != null) {
            response = driverService.getDriversByTeam(teamId);
        } else {
            response = driverService.getAllDrivers();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> updateDriver(
            @PathVariable UUID id, 
            @Valid @RequestBody UpdateDriverRequest request) {
        DriverResponse response = driverService.updateDriver(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable UUID id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }
}