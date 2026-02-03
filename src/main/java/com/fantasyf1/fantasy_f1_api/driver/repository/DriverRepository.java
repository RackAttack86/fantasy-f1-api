package com.fantasyf1.fantasy_f1_api.driver.repository;

import com.fantasyf1.fantasy_f1_api.driver.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID> {

    Optional<Driver> findByCode(String codes);

    Optional<Driver> findByNumber(Integer number);

    List<Driver> findByTeamId(UUID teamId);

    boolean existsByCode(String code); // duplicate checking
    
    boolean existsByNumber(Integer number); // duplicate checking
} 