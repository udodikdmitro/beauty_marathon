CREATE TABLE IF NOT EXISTS user_profile
  (
      id            BIGSERIAL PRIMARY KEY,
      name          TEXT          NOT NULL,
      start_weight  NUMERIC(6, 3) NOT NULL,
      target_weight NUMERIC(6, 4) NOT NULL,
      creation_date DATE DEFAULT NOW()
  );