CREATE TABLE drivers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    code VARCHAR(3) NOT NULL UNIQUE,
    number INTEGER NOT NULL UNIQUE,
    team_id UUID REFERENCES teams(id),
    nationality VARCHAR(50),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_drivers_code ON drivers(code);
CREATE INDEX idx_drivers_team_id ON drivers(team_id);