-- liquibase formatted sql

-- changeset dylan-behetre:index-1
-- add an index to optimize any request on the name property
CREATE INDEX IF NOT EXISTS idx_universe_name ON public.universe USING BTREE (name);

-- rollback DROP INDEX IF EXISTS idx_universe_name;
