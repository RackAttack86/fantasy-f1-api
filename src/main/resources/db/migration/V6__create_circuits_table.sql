-- Creates the circuits table for storing F1 track information
-- Each circuit has a unique name and stores track details like length and lap count

CREATE TABLE circuits (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
    name VARCHAR(100) NOT NULL UNIQUE, -- e.g., "Monaco", "Silverstone"
    location VARCHAR(100) NOT NULL, -- City where circuit is located
    country VARCHAR(100) NOT NULL, -- Country
    length_km DECIMAL(5, 3) NOT NULL, -- Track length in km (e.g., 5.891)
    number_of_laps INTEGER NOT NULL, -- Standard race lap count
    first_grand_prix_year INTEGER, -- Year of first GP (nullable)
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Index for fast lookups by name (unique constraint also creates an index)
CREATE INDEX idx_circuits_name ON circuits (name);

-- Index for filtering circuits by country
CREATE INDEX idx_circuits_country ON circuits (country);