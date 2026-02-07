package com.fantasyf1.fantasy_f1_api.raceresult.entity;

import com.fantasyf1.fantasy_f1_api.driver.entity.Driver;
import com.fantasyf1.fantasy_f1_api.race.entity.Race;
import com.fantasyf1.fantasy_f1_api.team.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "race_results")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaceResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "qualifying_position")
    private Integer qualifyingPositon;

    @Column(name = "sprint_position")
    private Integer sprintPosition;

    @Column(name = "sprint_points", precision = 5, scale = 2)
    private BigDecimal sprintPoints;

    @Column(name = "grid_position")
    private Integer gridPosition;

    @Column(name = "finish_position")
    private Integer finishPosition;

    @Column(name = "race_points", precision = 5, scale = 2)
    private BigDecimal racePoints;

    @Column(length = 50)
    private String status;

    @Column(name = "dnf_reason", length = 255)
    private String dnfReason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = Instant.now();
        updatedAt = Instant.now();
        if (status == null) {
            status = "FINISHED";
        }
        if (sprintPoints == null) {
            sprintPoints = BigDecimal.ZERO;
        }
        if (racePoints == null) {
            racePoints = BigDecimal.ZERO;
        }
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now();
    }
}
