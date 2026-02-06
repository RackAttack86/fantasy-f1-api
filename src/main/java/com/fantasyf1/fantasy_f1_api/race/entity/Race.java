package com.fantasyf1.fantasy_f1_api.race.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import com.fantasyf1.fantasy_f1_api.circuit.entity.Circuit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "races", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"season", "round"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Race {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private Integer season;

    @Column(nullable = false)
    private Integer round;

    // ManyToOne relationship: many races can be at one circuit (over different years)
    // LAZY fetch for performance (same pattern as Driver -> Team)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_id", nullable = false)
    private Circuit circuit;

    @Column(name = "race_date", nullable = false)
    private LocalDate raceDate;

    @Column(name = "qualifying_date")
    private LocalDate qualifyingDate;

    @Column(name = "sprint_date")
    private LocalDate sprintDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = Instant.now();
        updatedAt = Instant.now();        
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now();
    }
}
