ALTER TABLE mo_measurement
    ADD column month_number INT GENERATED ALWAYS
        AS (EXTRACT(MONTH FROM mo_date)::INT) STORED;

ALTER TABLE wk_measurement
    ADD column commentary TEXT;

ALTER TABLE wk_measurement
    DROP column week_number;