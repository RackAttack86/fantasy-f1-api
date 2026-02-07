CREATE TABLE driver_stats (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    race_result_id UUID NOT NULL REFERENCES race_results(id) ON DELETE CASCADE,

-- Qualifying stats
fastest_qualifying_lap_time VARCHAR(20),

-- Race stats
fastest_lap BOOLEAN DEFAULT FALSE,
fastest_lap_time VARCHAR(20),
positions_gained INTEGER DEFAULT 0, -- Can be negative
overtakes INTEGER DEFAULT 0,
laps_completed INTEGER DEFAULT 0,
pit_stops INTEGER DEFAULT 0,

-- Bonus indicators
pole_position BOOLEAN DEFAULT FALSE,
    led_any_lap BOOLEAN DEFAULT FALSE,
    finished_top_10 BOOLEAN DEFAULT FALSE,
    driver_of_the_day BOOLEAN DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_driver_stats_result UNIQUE (race_result_id)
);