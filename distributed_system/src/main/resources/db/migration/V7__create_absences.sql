CREATE TABLE absences(
    id                UUID NOT NULL DEFAULT gen_random_uuid(),
    staff_id          UUID NOT NULL,
    date              DATE NOT NULL,
    shift_id          UUID NOT NULL,
    marked_by         UUID NOT NULL,
    marked_at         TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_absence PRIMARY KEY (id),
    CONSTRAINT fk_staff_absence FOREIGN KEY(staff_id) REFERENCES staff(id),
    CONSTRAINT fk_shift_assignment FOREIGN KEY (shift_id) REFERENCES assignments (id),
    CONSTRAINT fk_absence_marked FOREIGN KEY (marked_by) REFERENCES staff(id)


);