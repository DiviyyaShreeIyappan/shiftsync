CREATE TABLE staff_skills(
    skill_id      UUID NOT NULL DEFAULT gen_random_uuid(),
    staff_id      UUID NOT NULL,
    department    VARCHAR(20) NOT NULL,
    certified_at  TIMESTAMP,

    CONSTRAINT pk_skills PRIMARY KEY (skill_id),
    CONSTRAINT fk_staff FOREIGN KEY (staff_id) REFERENCES staff (id),
    CONSTRAINT uk_staff_department UNIQUE (staff_id,department)

);