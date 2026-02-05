package com.fantasyf1.fantasy_f1_api.circuit.repository;

import com.fantasyf1.fantasy_f1_api.circuit.entity.Circuit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CircuitRepository extends JpaRepository<Circuit, UUID> {
    
    Optional<Circuit> findByName(String name);

    List<Circuit> findByCountry(String country);

    boolean existsByName(String name);
}
