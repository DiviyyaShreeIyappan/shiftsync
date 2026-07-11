CREATE TABLE shift_history(
    id                  UUID NOT NULL DEFAULT gen_random_uuid(),
    staff_id            UUID NOT NULL,
    week_start_date     DATE NOT NULL,
    shifts_count        INTEGER NOT NULL,
    total_hours         DECIMAL,

    CONSTRAINT pk_shift PRIMARY KEY (id),
    CONSTRAINT fk_staff FOREIGN KEY(staff_id) REFERENCES staff (id),
    CONSTRAINT uk_staff_start_date UNIQUE (staff_id,week_start_date)

);