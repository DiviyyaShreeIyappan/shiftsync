package com.example.distributed_system.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConflictDetectedEvent {
    private String eventId;
    private UUID conflictId;
    private UUID staffId;
    private UUID assignmentId1;
    private UUID assignmentId2;
    private LocalDateTime detectedAt;
}
