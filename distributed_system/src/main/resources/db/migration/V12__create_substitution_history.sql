CREATE TABLE substitution_history(
    id                            UUID NOT NULL DEFAULT gen_random_uuid(),
    staff_id                      UUID NOT NULL,
    date                          DATE NOT NULL,
    department                    VARCHAR(20) NOT NULL,
    outcome                       VARCHAR(20) NOT NULL,
    created_at                    TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_sub_his PRIMARY KEY (id),
    CONSTRAINT fk_staff_his FOREIGN KEY (staff_id) REFERENCES staff(id)

);