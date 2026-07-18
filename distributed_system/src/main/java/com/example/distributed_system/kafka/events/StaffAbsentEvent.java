package com.example.distributed_system.kafka.events;

import com.example.distributed_system.entities.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffAbsentEvent {
    private String eventId;
    private UUID absenceId;
    private UUID staffId;
    private UUID shiftId;
    private LocalDate date;
    private Department department;
}
