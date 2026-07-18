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
public class LeaveApprovedEvent {
    private String eventId;
    private UUID leaveRequestId;
    private UUID staffId;
    private UUID approvedById;
}
