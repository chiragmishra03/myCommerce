ALTER TABLE categories
DROP INDEX name,
ADD COLUMN is_active BOOLEAN GENERATED ALWAYS AS (deleted_at IS NULL) STORED,
ADD CONSTRAINT unique_name_active UNIQUE (name, is_active);