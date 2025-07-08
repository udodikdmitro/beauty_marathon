CREATE TABLE IF NOT EXISTS user_profile
  (
      id            BIGSERIAL PRIMARY KEY,
      name          TEXT          NOT NULL,
      start_weight  NUMERIC(6, 3) NOT NULL,
      target_weight NUMERIC(6, 3) NOT NULL,
      creation_date DATE DEFAULT NOW(),
      deleted_state VARCHAR(11) DEFAULT 'NOT_DELETED' NOT NULL
  );

CREATE TABLE IF NOT EXISTS mo_measurement
(
    id            BIGSERIAL PRIMARY KEY,
    mo_date DATE NOT NULL,
    year INT GENERATED ALWAYS AS (EXTRACT(YEAR FROM mo_date)::INT) STORED,
    closed_state VARCHAR(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS wk_measurement
(
    id            BIGSERIAL PRIMARY KEY,
    week_number SMALLINT NOT NULL,
    measurement_date DATE NOT NULL,
    month_id BIGINT NOT NULL CONSTRAINT wk_measurement_mo_measurement_fk
        REFERENCES mo_measurement(id)
        ON DELETE CASCADE,
    closed_state VARCHAR(6) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_measurement
(
    id            BIGSERIAL PRIMARY KEY,
    wk_measurement_id BIGINT NOT NULL CONSTRAINT user_measurement_wk_measurement_fk
        REFERENCES wk_measurement(id),
    weight NUMERIC(6,3) NOT NULL,
    weight_point SMALLINT NOT NULL,
    sleep_points SMALLINT NOT NULL,
    water_points SMALLINT NOT NULL,
    step_points SMALLINT NOT NULL,
    diary_points SMALLINT NOT NULL,
    alcohol_free_points SMALLINT NOT NULL,
    user_id BIGINT NOT NULL CONSTRAINT user_measurement_user_profile_fk
        REFERENCES user_profile(id)
        ON DELETE CASCADE,
    commentary TEXT
);