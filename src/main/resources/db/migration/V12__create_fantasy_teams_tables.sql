CREATE TABLE fantasy_teams (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id),
    name VARCHAR(100) NOT NULL,
    season INTEGER NOT NULL,

-- Mode: SEASON = season-long, RACE = race-by-race
mode VARCHAR(20) NOT NULL DEFAULT 'SEASON',

-- For race-by-race mode
race_id UUID REFERENCES races (id),

-- Pick limits
max_drivers INTEGER DEFAULT 5,
max_constructors INTEGER DEFAULT 2,

-- Calculated total fantasy points
total_points DECIMAL(10, 2) DEFAULT 0,

-- Status
is_active BOOLEAN DEFAULT TRUE,
locked BOOLEAN DEFAULT FALSE, -- Locked once race starts
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

-- Unique team per user per mode per season (or race)
CONSTRAINT uq_fantasy_team_user_season_mode UNIQUE (user_id, season, mode, race_id)
);

-- Individual driver/team picks
CREATE TABLE fantasy_picks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    fantasy_team_id UUID NOT NULL REFERENCES fantasy_teams(id) ON DELETE CASCADE,

-- Either driver OR constructor (not both)
driver_id UUID REFERENCES drivers (id),
team_id UUID REFERENCES teams (id),

-- Points earned by this pick
points_earned DECIMAL(7, 2) DEFAULT 0,

-- Pick order (for tiebreakers or captain selection)
pick_order INTEGER DEFAULT 0,
    is_captain BOOLEAN DEFAULT FALSE,  -- Captain gets 2x points

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_pick_type CHECK (
        (driver_id IS NOT NULL AND team_id IS NULL) OR
        (driver_id IS NULL AND team_id IS NOT NULL)
    )
);