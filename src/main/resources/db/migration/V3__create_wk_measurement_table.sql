CREATE TABLE IF NOT EXISTS wk_measurement
(
    id            BIGSERIAL PRIMARY KEY,
    week_number SMALLINT NOT NULL,
    measurement_date DATE NOT NULL,
    month_id BIGINT NOT NULL CONSTRAINT wk_measurement_mo_measurement_fk
        REFERENCES mo_measurement(id)
        ON DELETE CASCADE
);