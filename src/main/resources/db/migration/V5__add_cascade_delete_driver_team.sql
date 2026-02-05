-- Drop existing foreign key constraint
ALTER Table drivers
Drop Constraint drivers_team_id_fkey;

-- Re-add with ON DELETE SET NULL
Alter Table drivers
Add Constraint drivers_team_id_fkey
Foreign KEY (team_id) REFERENCES teams(id)
ON DELETE SET NULL;
