CREATE TABLE schedule_assignments(
    id                            UUID NOT NULL DEFAULT gen_random_uuid(),
    schedule_id                   UUID NOT NULL,
    assignment_id                 UUID NOT NULL,

    CONSTRAINT pk_schedule_assignment PRIMARY KEY (id),
    CONSTRAINT fk_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (id),
    CONSTRAINT fk_assignment FOREIGN KEY (assignment_id) REFERENCES assignments (id),
    CONSTRAINT uk_schedule_assignment UNIQUE (schedule_id,assignment_id)
);