CREATE TABLE scoring_rules (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    season INTEGER,  -- NULL = default rules, specific year = season-specific

-- Position points (JSON for flexibility)
qualifying_points JSONB NOT NULL DEFAULT '{"1":10,"2":9,"3":8,"4":7,"5":6,"6":5,"7":4,"8":3,"9":2,"10":1}',
race_points JSONB NOT NULL DEFAULT '{"1":25,"2":18,"3":15,"4":12,"5":10,"6":8,"7":6,"8":4,"9":2,"10":1}',
sprint_points JSONB NOT NULL DEFAULT '{"1":8,"2":7,"3":6,"4":5,"5":4,"6":3,"7":2,"8":1}',

-- Bonus points
fastest_lap_points DECIMAL(5, 2) DEFAULT 5,
pole_position_points DECIMAL(5, 2) DEFAULT 5,
driver_of_day_points DECIMAL(5, 2) DEFAULT 10,
positions_gained_multiplier DECIMAL(3, 2) DEFAULT 2, -- Points per position gained
overtake_points DECIMAL(3, 2) DEFAULT 0.5,

-- Penalties
dnf_penalty DECIMAL(5,2) DEFAULT -10,
    dsq_penalty DECIMAL(5,2) DEFAULT -20,

    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);