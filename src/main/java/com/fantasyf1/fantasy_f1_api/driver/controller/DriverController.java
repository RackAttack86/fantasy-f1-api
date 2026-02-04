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
    
}
