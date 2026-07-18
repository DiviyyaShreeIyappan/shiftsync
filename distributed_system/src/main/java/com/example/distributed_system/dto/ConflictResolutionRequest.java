package com.example.distributed_system.dto;

import com.example.distributed_system.entities.Assignment;
import com.example.distributed_system.entities.enums.ConflictResolvedBy;
import lombok.Data;

@Data
public class ConflictResolutionRequest {
    private Assignment winningAssignment;
    private ConflictResolvedBy resolvedBy;
    private String resolutionReason;
}
