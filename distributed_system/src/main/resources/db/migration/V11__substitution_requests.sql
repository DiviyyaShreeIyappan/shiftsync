CREATE TABLE substitution_requests(
    id                               UUID  NOT NULL DEFAULT gen_random_uuid(),
    candidate_staff_id               UUID NOT NULL,
    assignment_id                    UUID NOT NULL,
    rank                             INTEGER NOT NULL,
    status                           VARCHAR(20) NOT NULL,
    contacted_at                     TIMESTAMP NOT NULL DEFAULT NOW(),
    responded_at                     TIMESTAMP,
    response                         VARCHAR(20),
    channel                          VARCHAR(20) NOT NULL,

    CONSTRAINT pk_request_id PRIMARY KEY (id),
    CONSTRAINT fk_candidate_staff FOREIGN KEY (candidate_staff_id) REFERENCES staff (id),
    CONSTRAINT fk_assignment_req  FOREIGN KEY (assignment_id) REFERENCES assignments (id)
);