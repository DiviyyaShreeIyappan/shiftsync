package com.example.distributed_system.kafka.events;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftCancelledEvent {
    private String eventId;        // for idempotency
    private UUID assignmentId;     // which assignment
    private UUID staffId;          // who was assigned
    private String department;     // which department
    private LocalDate date;        // which day
}
