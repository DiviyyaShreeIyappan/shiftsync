package com.example.distributed_system.entities.enums;

public enum UnavailabilityStatus {
    ABSENT,     // marked absent today — real time
    EXCLUDED,   // on approved leave — hard exclusion
    FLAGGED,    // flagged this day — soft warning
    AVAILABLE
}
