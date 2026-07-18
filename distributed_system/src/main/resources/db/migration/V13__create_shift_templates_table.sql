CREATE TABLE shift_templates
(
    id             UUID        NOT NULL DEFAULT gen_random_uuid(),
    department     VARCHAR(20) NOT NULL,
    start_time     TIME        NOT NULL,
    end_time       TIME        NOT NULL,
    staff_required INTEGER     NOT NULL,
    applicable_days VARCHAR(50) NOT NULL,
    active         BOOLEAN     NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_shift_templates PRIMARY KEY (id)
);