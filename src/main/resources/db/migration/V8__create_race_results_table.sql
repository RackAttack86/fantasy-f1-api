CREATE TABLE race_results (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    race_id UUID NOT NULL REFERENCES races(id),
    driver_id UUID NOT NULL REFERENCES drivers(id),
    team_id UUID REFERENCES teams(id),

-- Qualifying results
qualifying_position INTEGER,

-- Sprint results (nullable - not all races have sprints)
sprint_position INTEGER, sprint_points DECIMAL(5, 2) DEFAULT 0,

-- Race results
grid_position INTEGER,
finish_position INTEGER,
race_points DECIMAL(5, 2) DEFAULT 0,

-- Status
status VARCHAR(50) DEFAULT 'FINISHED', -- FINISHED, DNF, DNS, DSQ
    dnf_reason VARCHAR(255),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_race_results_race_driver UNIQUE (race_id, driver_id)
);