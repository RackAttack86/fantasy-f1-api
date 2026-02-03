package com.fantasyf1.fantasy_f1_api.driver.entity;

import com.fantasyf1.fantasy_f1_api.team.entity.Team;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "drivers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 3)
    private String code; // 3 letter driver code (e.g., "VER", "HAM", "NOR")

    @Column(nullable =  false, unique = true)
    private Integer number; // Drivers racing number. Unique per driver

    @ManyToOne(fetch = FetchType.LAZY) // Driver belongs to one team, lazy loading for performance
    @JoinColumn(name = "team_id") // Maps to the foreign key column
    private Team team;

    @Column(length = 50)
    private String nationality;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
