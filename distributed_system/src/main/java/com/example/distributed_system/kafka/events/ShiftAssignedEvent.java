package com.example.distributed_system.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftAssignedEvent {
    private String eventId;        // for idempotency
    private UUID assignmentId;     // which assignment
    private UUID staffId;          // who was assigned
    private UUID assignedById;     // which manager assigned
    private String department;     // which department
    private LocalDate date;        // which day
    private LocalTime startTime;   // shift start
    private LocalTime endTime;     // shift end
}