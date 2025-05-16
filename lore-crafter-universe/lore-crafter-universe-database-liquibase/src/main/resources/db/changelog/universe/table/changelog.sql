-- liquibase formatted sql

-- changeset dylan-behetre:table-1
-- Create the required Universe table related to the universe entity
CREATE TABLE IF NOT EXISTS public.universe
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) UNIQUE NOT NULL,
    description TEXT
);
-- rollback DROP TABLE IF EXISTS public.universe;