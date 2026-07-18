
package com.example.distributed_system.controllers;

import com.example.distributed_system.entities.SubstitutionRequest;
import com.example.distributed_system.entities.enums.SubstitutionResponse;
import com.example.distributed_system.services.SubstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/substitutions")
@RequiredArgsConstructor
public class SubstitutionController {

    private final SubstitutionService substitutionService;

    @GetMapping("/{assignmentId}/status")
    public SubstitutionRequest getSubstitutionStatus(
            @PathVariable UUID assignmentId) {
        return substitutionService.getSubstitutionStatus(assignmentId);
    }

    @PostMapping("/{requestId}/respond")
    public ResponseEntity<Void> handleResponse(
            @PathVariable UUID requestId,
            @RequestParam SubstitutionResponse response) {
        substitutionService.handleResponse(requestId, response);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{requestId}/timeout")
    public ResponseEntity<Void> handleTimeout(
            @PathVariable UUID requestId) {
        substitutionService.handleTimeout(requestId);
        return ResponseEntity.ok().build();
    }
}