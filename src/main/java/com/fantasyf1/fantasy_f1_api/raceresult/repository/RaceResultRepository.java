package com.fantasyf1.fantasy_f1_api.raceresult.repository;

//**Purpose:** Spring Data JPA repository interface that provides database operations for RaceResult entities. Spring automatically implements this interface at runtime.

import com.fantasyf1.fantasy_f1_api.raceresult.entity.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository // Marks this as a spring-managed repository bean. Also enables exception translation (database exceptions become spring's DataAccessException)
public interface RaceResultRepository extends JpaRepository<RaceResult, UUID> {
    
    List<RaceResult> findByRaceId(UUID raceId);

    List<RaceResult> findByDriverId(UUID driverId);

    List<RaceResult> findByRaceIdOrderByFinishPositionAsc(UUID raceId);

    Optional<RaceResult> findByRaceIdAndDriverId(UUID raceId, UUID driverId);

    boolean existsByRaceIdAndDriverId(UUID raceId, UUID driverId);
}

/*
 ** Inherited Methods from JpaRepository:**
 * - `save(entity)` - Insert or update
 * - `findById(id)` - Find by primary key
 * - `findAll()` - Get all records
 * - `deleteById(id)` - Delete by primary key
 * - `existsById(id)` - Check existence
 */