package com.fantasyf1.fantasy_f1_api.race.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fantasyf1.fantasy_f1_api.race.entity.Race;

@Repository
public interface RaceRepository extends JpaRepository<Race, UUID> {

    List<Race> findBySeason(Integer season);

    List<Race> findByCircuitId(UUID circuitId);
    
    Optional<Race> findBySeasonAndRound(Integer season, Integer round);

    boolean existsBySeasonAndRound(Integer season, Integer round);

    // Returns races in a season ordered by round number
    List<Race> findBySeasonOrderByRoundAsc(Integer season);
    
}
