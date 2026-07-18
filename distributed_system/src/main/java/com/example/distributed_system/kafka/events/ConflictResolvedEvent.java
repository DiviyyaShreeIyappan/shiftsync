package com.example.distributed_system.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConflictResolvedEvent {
    private String eventId;
    private UUID conflictId;
    private UUID winningAssignmentId;
    private UUID losingAssignmentId;
    private String resolvedBy;
    private String resolutionReason;
}
