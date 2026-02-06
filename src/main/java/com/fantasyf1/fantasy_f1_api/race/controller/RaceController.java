package com.fantasyf1.fantasy_f1_api.race.controller;

import com.fantasyf1.fantasy_f1_api.race.dto.CreateRaceRequest;
import com.fantasyf1.fantasy_f1_api.race.dto.RaceResponse;
import com.fantasyf1.fantasy_f1_api.race.dto.UpdateRaceRequest;
import com.fantasyf1.fantasy_f1_api.race.service.RaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/races")
@RequiredArgsConstructor
public class RaceController {
    
    private final RaceService raceService;

    @PostMapping
    public ResponseEntity<RaceResponse> createRace(@Valid @RequestBody CreateRaceRequest request) {
        RaceResponse response = raceService.createRace(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceResponse> getRace(@PathVariable UUID id) {
        RaceResponse response = raceService.getRaceById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/season/{season}/round/{round}")
    public ResponseEntity<RaceResponse> getRaceBySeasonAndRound(
            @PathVariable Integer season, 
            @PathVariable Integer round) {
        RaceResponse response = raceService.getRaceBySeasonAndRound(season, round);
        return ResponseEntity.ok(response);
    }

    // GET /api/v1/races - all races
    // GET /api/v1/races?season=2024 - filter by season
    // GET /api/v1/races?circuitId=xxx - filter by circuit
    // TODO: What if both season and circuitID are included? It will only return season results not both.
    @GetMapping
    public ResponseEntity<List<RaceResponse>> getAllRaces(
            @RequestParam(required = false) Integer season,
            @RequestParam(required = false) UUID circuitId) {
        List<RaceResponse> response;
        if (season != null) {
            response = raceService.getRacesBySeason(season);
        } else if (circuitId != null) {
            response = raceService.getRacesByCircuit(circuitId);
        } else {
            response = raceService.getAllRaces();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaceResponse> updateRace(
                @PathVariable UUID id, 
                @Valid @RequestBody UpdateRaceRequest request) {
        RaceResponse response = raceService.updateRace(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable UUID id) {
        raceService.deleteRace(id);
        return ResponseEntity.noContent().build();
    }

}
