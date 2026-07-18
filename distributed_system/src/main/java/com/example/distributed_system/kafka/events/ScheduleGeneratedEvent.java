package com.example.distributed_system.kafka.events;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleGeneratedEvent {
    private String eventId;
    private UUID scheduleId;
    private LocalDate weekStartDate;
    private UUID managerId;
}