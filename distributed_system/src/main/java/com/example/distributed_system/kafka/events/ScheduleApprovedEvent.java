package com.example.distributed_system.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleApprovedEvent {
    private String eventId;
    private UUID scheduleId;
    private UUID approvedById;
    private LocalDateTime approvedAt;
}
