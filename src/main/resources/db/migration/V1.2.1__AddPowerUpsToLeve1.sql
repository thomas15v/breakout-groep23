ALTER TABLE level_map DROP COLUMN BlockCat;
ALTER TABLE level_map ADD COLUMN PowerUpType int not NULL DEFAULT 1;