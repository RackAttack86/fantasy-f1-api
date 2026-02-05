package com.fantasyf1.fantasy_f1_api.circuit.service;

import com.fantasyf1.fantasy_f1_api.common.exception.DuplicateResourceException;
import com.fantasyf1.fantasy_f1_api.common.exception.ResourceNotFoundException;
import com.fantasyf1.fantasy_f1_api.circuit.dto.CreateCircuitRequest;
import com.fantasyf1.fantasy_f1_api.circuit.dto.CircuitResponse;
import com.fantasyf1.fantasy_f1_api.circuit.dto.UpdateCircuitRequest;
import com.fantasyf1.fantasy_f1_api.circuit.entity.Circuit;
import com.fantasyf1.fantasy_f1_api.circuit.mapper.CircuitMapper;
import com.fantasyf1.fantasy_f1_api.circuit.repository.CircuitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CircuitService {
    
    private final CircuitRepository circuitRepository;
    private final CircuitMapper circuitMapper;

    @Transactional
    public CircuitResponse createCircuit(CreateCircuitRequest request) {
        if (circuitRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Circuit", "name", request.getName());
        }

        Circuit circuit = Circuit.builder()
                .name(request.getName())
                .location(request.getLocation())
                .country(request.getCountry())
                .lengthKm(request.getLengthKm())
                .numberOfLaps(request.getNumberOfLaps())
                .firstGrandPrixYear(request.getFirstGrandPrixYear())
                .build();

        Circuit savedDCircuit = circuitRepository.save(circuit);
        return circuitMapper.toResponse(savedDCircuit);
    }

    @Transactional(readOnly = true)
    public CircuitResponse getCircuitById(UUID id) {
        Circuit circuit = circuitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Circuit", "id", id.toString()));
        return circuitMapper.toResponse(circuit);
    }

    @Transactional(readOnly = true)
    public List<CircuitResponse> getCircuitsByCountry(String country) {
        return circuitRepository.findByCountry(country).stream()
                .map(circuitMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CircuitResponse> getAllCircuits() {
        return circuitRepository.findAll().stream()
                .map(circuitMapper::toResponse)
                .toList();
    }

    @Transactional
    public CircuitResponse updateCircuit(UUID id, UpdateCircuitRequest request) {
        Circuit circuit = circuitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Circuit", 
                "id", id.toString()));
        
        if (request.getName() != null && !request.getName().equals(circuit.getName())) {
            if (circuitRepository.existsByName(request.getName())) {
                throw new DuplicateResourceException("Circuit", "name", request.getName());
            }
        }
        circuit.setName(request.getName());

        if (request.getLocation() != null) {
            circuit.setLocation(request.getLocation());
        }

        if (request.getCountry() != null) {
            circuit.setCountry(request.getCountry());
        }

        if (request.getLengthKm() != null) {
            circuit.setLengthKm(request.getLengthKm());
        }

        if (request.getNumberOfLaps() != null) {
            circuit.setNumberOfLaps(request.getNumberOfLaps());
        }

        if (request.getFirstGrandPrixYear() != null) {
            circuit.setFirstGrandPrixYear(request.getFirstGrandPrixYear());
        }

        Circuit updatedCircuit = circuitRepository.save(circuit);
        return circuitMapper.toResponse(updatedCircuit);
    }

    @Transactional
    public void deleteCircuit(UUID id) {
        if (!circuitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Circuit", "id", id.toString());
        }
        circuitRepository.deleteById(id);
    }

}
