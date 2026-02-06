package com.fantasyf1.fantasy_f1_api.race.service;

import com.fantasyf1.fantasy_f1_api.common.exception.DuplicateResourceException;
import com.fantasyf1.fantasy_f1_api.common.exception.ResourceNotFoundException;
import com.fantasyf1.fantasy_f1_api.circuit.entity.Circuit;
import com.fantasyf1.fantasy_f1_api.circuit.repository.CircuitRepository;
import com.fantasyf1.fantasy_f1_api.race.dto.CreateRaceRequest;
import com.fantasyf1.fantasy_f1_api.race.dto.RaceResponse;
import com.fantasyf1.fantasy_f1_api.race.dto.UpdateRaceRequest;
import com.fantasyf1.fantasy_f1_api.race.entity.Race;
import com.fantasyf1.fantasy_f1_api.race.mapper.RaceMapper;
import com.fantasyf1.fantasy_f1_api.race.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RaceService {
    private final RaceRepository raceRepository;
    private final CircuitRepository circuitRepository;
    private final RaceMapper raceMapper;

    @Transactional
    public RaceResponse createRace(CreateRaceRequest request) {
        // Check for duplicate season + round combination
        if (raceRepository.existsBySeasonAndRound(request.getSeason(), request.getRound())) {
            throw new DuplicateResourceException("Race", "season+round", request.getSeason() + " Round " + request.getRound());
        }

        Circuit circuit = circuitRepository.findById(request.getCircuitId())
                .orElseThrow(() -> new ResourceNotFoundException("Circuit", "id", request.getCircuitId().toString()));

        Race race = Race.builder()
                .name(request.getName())
                .season(request.getSeason())
                .round(request.getRound())
                .circuit(circuit)
                .raceDate(request.getRaceDate())
                .qualifyingDate(request.getQualifyingDate())
                .sprintDate(request.getSprintDate())
                .build();

        Race savedRace = raceRepository.save(race);
        return raceMapper.toResponse(savedRace);
    }

    @Transactional(readOnly = true)
    public RaceResponse getRaceById(UUID id) {
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Race", "id", id.toString()));
        return raceMapper.toResponse(race);
    }

    @Transactional(readOnly = true)
    public RaceResponse getRaceBySeasonAndRound(Integer season, Integer round) {
        Race race = raceRepository.findBySeasonAndRound(season, round)
                .orElseThrow(() -> new ResourceNotFoundException("Race", "season+round", season + " round " + round));
        return raceMapper.toResponse(race);
    }

    @Transactional(readOnly = true)
    public List<RaceResponse> getAllRaces() {
        return raceRepository.findAll().stream().map(raceMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<RaceResponse> getRacesBySeason(Integer season) {
        return raceRepository.findBySeasonOrderByRoundAsc(season).stream()
                .map(raceMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<RaceResponse> getRacesByCircuit(UUID circuitId) {
        if (!circuitRepository.existsById(circuitId)) {
            throw new ResourceNotFoundException("Circuit", 
                    "id", circuitId.toString());
        }

        return raceRepository.findByCircuitId(circuitId).stream()
                .map(raceMapper::toResponse).toList();
    }

    @Transactional
    public RaceResponse updateRace(UUID id, UpdateRaceRequest request) {
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Race", "id", id.toString()));
        
        // Check season+round uniqueness if either is being changed
        Integer newSeason = request.getSeason() != null ? request.getSeason() : race.getSeason();
        Integer newRound = request.getRound() != null ? request.getRound() : race.getRound();

        if (!newSeason.equals(race.getSeason()) || !newRound.equals(race.getRound())) {
            if (raceRepository.existsBySeasonAndRound(newSeason, newRound)) {
                throw new DuplicateResourceException("Race", "season+round", newSeason + " Round " + newRound);
            }
        }

        // Update only provided fields
        if (request.getName() != null) {
            race.setName(request.getName());
        }
        if (request.getSeason() != null) {
            race.setSeason(request.getSeason());
        }
        if (request.getRound() != null) {
            race.setRound(request.getRound());
        }
        if (request.getCircuitId() != null) {
            Circuit circuit = circuitRepository.findById(request.getCircuitId())
                    .orElseThrow(() -> new ResourceNotFoundException("Circuit", "id", request.getCircuitId().toString()));
            race.setCircuit(circuit);
        }
        if (request.getRaceDate() != null) {
            race.setRaceDate(request.getRaceDate());
        }
        if (request.getQualifyingDate() != null) {
            race.setQualifyingDate(request.getQualifyingDate());
        }
        if (request.getSprintDate() != null) {
            race.setSprintDate(request.getSprintDate());
        }

        Race updatedRace = raceRepository.save(race);
        return raceMapper.toResponse(updatedRace);
    }

    @Transactional
    public void deleteRace(UUID id) {
        if (!raceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Race", "id", id.toString());
        }
        raceRepository.deleteById(id);
    }
}
