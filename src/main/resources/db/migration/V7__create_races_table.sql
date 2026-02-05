-- Creates the races table for storing Grand Prix events
-- Each race belongs to a circuit and has a unique season+round combination

CREATE TABLE races (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,                  -- e.g., "2024 Monaco Grand Prix"
    season INTEGER NOT NULL,                     -- Year of the race
    round INTEGER NOT NULL,                      -- Round number in the season (1-24)
    circuit_id UUID NOT NULL REFERENCES circuits(id),  -- FK to circuits table
    race_date DATE NOT NULL,                     -- Main race date
    qualifying_date DATE,                        -- Qualifying session date (optional)
    sprint_date DATE,                            -- Sprint race date (optional, only some races)
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

-- Ensures no duplicate races in the same season and round
CONSTRAINT uq_races_season_round UNIQUE (season, round) );

-- Index for filtering races by season (common query)
CREATE INDEX idx_races_season ON races (season);

-- Index for finding all races at a specific circuit
CREATE INDEX idx_races_circuit_id ON races (circuit_id);

-- Index for chronological queries
CREATE INDEX idx_races_race_date ON races (race_date);