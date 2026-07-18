package com.example.distributed_system.dto;

import com.example.distributed_system.entities.enums.Department;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class AssignmentRequest {
    private String eventId;
    private UUID staffId;
    private Department department;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private UUID assignedById;
}