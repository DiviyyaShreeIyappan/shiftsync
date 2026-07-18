package com.example.distributed_system.controllers;

import com.example.distributed_system.dto.ConflictResolutionRequest;
import com.example.distributed_system.entities.Conflict;
import com.example.distributed_system.services.ConflictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conflicts")
@RequiredArgsConstructor
public class ConflictController {
    private final ConflictService conflictService;

    @GetMapping("/staff/{staffId}")
    public List<Conflict> getConflictsByStaffId(@PathVariable UUID staffId){
        return conflictService.getConflictsByStaffId(staffId);
    }
    @GetMapping("/unresolved")
    public List<Conflict> getUnresolvedConflicts() {
        return conflictService.getUnresolvedConflicts();
    }
    @PutMapping("/{conflictId}/resolve")
    public Conflict resolveConflict(
            @PathVariable UUID conflictId,
            @RequestBody ConflictResolutionRequest request) {
        return conflictService.resolveConflict(
                conflictId,
                request.getWinningAssignment(),
                request.getResolvedBy(),
                request.getResolutionReason()
        );
    }
}
