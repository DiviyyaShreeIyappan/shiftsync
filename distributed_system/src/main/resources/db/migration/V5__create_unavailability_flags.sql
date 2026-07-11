CREATE TABLE unavailability_flags(
    id                            UUID NOT NULL DEFAULT gen_random_uuid(),
    staff_id                      UUID NOT NULL,
    date                          DATE NOT NULL,
    reason                        VARCHAR(1000),
    status                        VARCHAR(20) NOT NULL,
    override_by                   UUID,
    created_at                    TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_unavailability PRIMARY KEY (id),
    CONSTRAINT fk_staff_unavailability FOREIGN KEY (staff_id) REFERENCES staff (id),
    CONSTRAINT fk_staff_override FOREIGN KEY (override_by) REFERENCES staff(id),
    CONSTRAINT uk_staff_date UNIQUE(staff_id,date)
);