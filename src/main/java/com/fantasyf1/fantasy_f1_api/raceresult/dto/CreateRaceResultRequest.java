package com.fantasyf1.fantasy_f1_api.raceresult.dto;

// **Purpose:** Data transfer object for creating new race results. Contains validation rules and only the fields that should be provided during creation.

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data // Lombok annotation that generates getters, setters, equals(), hashcode(), and toString() methods automatically. Reduces boilerplate code.
@Builder // Lombok annotation that implements the builder pattern, allowing fluent object construction
@NoArgsConstructor // Lombok annotation generates a no-argument constructor. **Required by JPA** - Hibernate needs this to instantiate entities when loading from database
@AllArgsConstructor // Lombok generates a constructor with all fields. Used by the builder pattern internally.
public class CreateRaceResultRequest {
    
    @NotNull(message = "Driver Id is required")
    private UUID driverId;

    private UUID teamId;

    @Min(value = 1, message = "Qualifying position must be at least 1")
    @Max(value = 24, message = "Qualifying position must be at most 24")
    private Integer qualifyingPosition;

    @Min(value = 1, message = "Sprint position must be at least 1")
    @Max(value = 24, message = "Spring position must be at most 24")
    private Integer sprintPosition;

    private BigDecimal sprintPoints;

    @Min(value = 1, message = "Grid position must be at least 1")
    @Max(value = 24, message = "Grid position must be at most 24")
    private Integer gridPosition;

    @Min(value = 1, message = "Finish position must be at least 1")
    @Max(value = 24, message = "Finish position must be at most 24")
    private Integer finishPosition;

    private BigDecimal racePoints;

    private String status; // FINISHED, DNF, DNS, DSQ

    private String dnfReason;
}

/*
 ** Why DTOs instead of using Entity directly?**
 * 1. **Security** - Never expose internal fields (like `createdAt`, `id` before creation)
 * 2. **Validation** - Validation belongs on input DTOs, not on entities
 * 3. **Flexibility** - Request/response shapes can differ from database
 * structure
 * 4. **Versioning** - API can evolve independently of database schema
 */
