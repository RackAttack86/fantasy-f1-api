package com.fantasyf1.fantasy_f1_api.circuit.controller;

import com.fantasyf1.fantasy_f1_api.circuit.dto.CreateCircuitRequest;
import com.fantasyf1.fantasy_f1_api.circuit.dto.CircuitResponse;
import com.fantasyf1.fantasy_f1_api.circuit.dto.UpdateCircuitRequest;
import com.fantasyf1.fantasy_f1_api.circuit.service.CircuitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/circuits")
@RequiredArgsConstructor
public class CircuitController {
    
    private final CircuitService circuitService;

    @PostMapping
    public ResponseEntity<CircuitResponse> createCircuit(@Valid @RequestBody CreateCircuitRequest request) {
        CircuitResponse response = circuitService.createCircuit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CircuitResponse> getCircuit(@PathVariable UUID id) {
        CircuitResponse response = circuitService.getCircuitById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<CircuitResponse>> getAllCircuits(@RequestParam(required = false) String country){
        List<CircuitResponse> response;
        if (country != null) {
            response = circuitService.getCircuitsByCountry(country);
        } else {
            response = circuitService.getAllCircuits();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CircuitResponse> updateCircuit(@PathVariable UUID id, 
        @Valid @RequestBody UpdateCircuitRequest request) {
        CircuitResponse response = circuitService.updateCircuit(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCircuit(@PathVariable UUID id) {
        circuitService.deleteCircuit(id);
        return ResponseEntity.noContent().build();
    }
}
