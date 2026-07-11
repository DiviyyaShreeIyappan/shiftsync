CREATE TABLE conflicts(
    id                 UUID NOT NULL DEFAULT gen_random_uuid(),
    staff_id           UUID NOT NULL,
    assignment_id_1    UUID NOT NULL,
    assignment_id_2    UUID NOT NULL,
    detected_at        TIMESTAMP NOT NULL DEFAULT NOW(),
    resolved_at        TIMESTAMP,
    winning_assignment  UUID ,
    resolved_by         VARCHAR(20),
    resolution_reason   TEXT,

    CONSTRAINT pk_conflict_id PRIMARY KEY (id),
    CONSTRAINT fk_staff_conflict FOREIGN KEY (staff_id) REFERENCES staff (id),
    CONSTRAINT fk_assignment_1 FOREIGN KEY (assignment_id_1) REFERENCES assignments (id),
    CONSTRAINT fk_assignment_2 FOREIGN KEY (assignment_id_2) REFERENCES assignments (id),
    CONSTRAINT fk_assignment_win FOREIGN KEY (winning_assignment) REFERENCES assignments (id)
);