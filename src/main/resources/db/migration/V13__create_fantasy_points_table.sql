CREATE TABLE fantasy_points (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    fantasy_pick_id UUID NOT NULL REFERENCES fantasy_picks(id) ON DELETE CASCADE,
    race_id UUID NOT NULL REFERENCES races(id),

-- Breakdown of points
qualifying_points DECIMAL(5, 2) DEFAULT 0,
sprint_points DECIMAL(5, 2) DEFAULT 0,
race_points DECIMAL(5, 2) DEFAULT 0,
bonus_points DECIMAL(5, 2) DEFAULT 0,
penalty_points DECIMAL(5, 2) DEFAULT 0,

-- Captain multiplier applied
captain_multiplier DECIMAL(3, 2) DEFAULT 1,

-- Total for this pick for this race
total_points DECIMAL(7,2) DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_fantasy_points_pick_race UNIQUE (fantasy_pick_id, race_id)
);