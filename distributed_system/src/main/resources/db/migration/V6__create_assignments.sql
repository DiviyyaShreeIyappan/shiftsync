CREATE TABLE assignments(
    id                   UUID NOT NULL DEFAULT gen_random_uuid(),
    staff_id             UUID NOT NULL,
    department           VARCHAR(20) NOT NULL,
    date                 DATE NOT NULL,
    start_time           TIME NOT NULL,
    end_time             TIME NOT NULL,
    status               VARCHAR(20) NOT NULL,
    assigned_by          UUID,
    created_at           TIMESTAMP NOT NULL DEFAULT NOW(),
    event_id             VARCHAR(100) NOT NULL,

    CONSTRAINT pk_assignments PRIMARY KEY (id),
    CONSTRAINT fk_staff_assignment FOREIGN KEY (staff_id) REFERENCES staff(id),
    CONSTRAINT fk_staff_assignee FOREIGN KEY (assigned_by) REFERENCES staff(id),
    CONSTRAINT uk_staff_shift_window UNIQUE (staff_id,date,start_time,end_time),
    CONSTRAINT uk_event_id UNIQUE (event_id)

);