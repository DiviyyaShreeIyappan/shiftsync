ALTER TABLE conflicts
    ADD COLUMN winning_assignment_id UUID,
    ADD COLUMN resolved_by VARCHAR(20),
    ADD COLUMN resolution_reason TEXT,
    ADD CONSTRAINT fk_conflict_winning
        FOREIGN KEY (winning_assignment_id)
            REFERENCES assignments(id);