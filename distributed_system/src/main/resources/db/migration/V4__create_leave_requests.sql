CREATE TABLE leave_requests(
    id                      UUID NOT NULL DEFAULT gen_random_uuid(),
    staff_id                UUID NOT NULL,
    start_date              DATE NOT NULL,
    end_date                DATE NOT NULL,
    reason                  VARCHAR(200),
    status                  VARCHAR(20) NOT NULL,
    approved_by             UUID ,
    approved_at             TIMESTAMP,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_leave PRIMARY KEY (id),
    CONSTRAINT fk_staff FOREIGN KEY (staff_id) REFERENCES staff (id),
    CONSTRAINT fk_approval FOREIGN KEY (approved_by) REFERENCES staff (id)

);