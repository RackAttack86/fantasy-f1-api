package com.fantasyf1.fantasy_f1_api.team.repository;

import com.fantasyf1.fantasy_f1_api.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    
    Optional<Team> findByName(String name); // Lookup team by their short name

    boolean existsByName(String name); // For duplicate checking before creation
}
