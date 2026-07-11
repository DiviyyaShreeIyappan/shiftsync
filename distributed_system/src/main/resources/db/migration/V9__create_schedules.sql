CREATE TABLE schedules(
    id                      UUID NOT NULL DEFAULT gen_random_uuid(),
    week_start_date         DATE NOT NULL,
    status                  VARCHAR(20) NOT NULL,
    generated_at            TIMESTAMP NOT NULL DEFAULT NOW(),
    approved_by             UUID,
    approved_at             TIMESTAMP,

    CONSTRAINT pk_schedule_id PRIMARY KEY (id),
    CONSTRAINT fk_approved_by FOREIGN KEY (approved_by) REFERENCES staff (id)

);