CREATE TABLE driver_standings (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
    season INTEGER NOT NULL,
    driver_id UUID NOT NULL REFERENCES drivers (id),
    team_id UUID REFERENCES teams (id),
    total_points DECIMAL(7, 2) DEFAULT 0,
    position INTEGER,
    wins INTEGER DEFAULT 0,
    podiums INTEGER DEFAULT 0,
    pole_positions INTEGER DEFAULT 0,
    fastest_laps INTEGER DEFAULT 0,
    dnfs INTEGER DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_driver_standing_season UNIQUE (season, driver_id)
);

CREATE TABLE team_standings (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
    season INTEGER NOT NULL,
    team_id UUID NOT NULL REFERENCES teams (id),
    total_points DECIMAL(7, 2) DEFAULT 0,
    position INTEGER,
    wins INTEGER DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_team_standing_season UNIQUE (season, team_id)
);