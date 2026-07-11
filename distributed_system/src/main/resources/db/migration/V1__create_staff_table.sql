CREATE TABLE staff
(
    id               UUID  DEFAULT gen_random_uuid(),
    name             VARCHAR(100) NOT NULL,
    email            VARCHAR(100) NOT NULL,
    phone            VARCHAR(20),
    role             VARCHAR(20)  NOT NULL,
    contract_type    VARCHAR(10)  NOT NULL,
    contracted_hours INTEGER,
    created_at       TIMESTAMP    NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_staff PRIMARY KEY (id),
    CONSTRAINT uk_staff_email UNIQUE (email)
);