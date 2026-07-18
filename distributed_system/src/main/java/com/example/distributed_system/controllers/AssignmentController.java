package com.example.distributed_system.controllers;

import com.example.distributed_system.dto.AssignmentRequest;
import com.example.distributed_system.entities.Assignment;
import com.example.distributed_system.repositories.AssignmentRepository;
import com.example.distributed_system.services.AssignmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;


    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }
    @GetMapping("/{id}")
    public Assignment getAssignmentById(@PathVariable UUID id) {
        return assignmentService.getAssignmentById(id);
    }
    @GetMapping("/date/{date}")
    public List<Assignment> getAssignmentsByDate(@PathVariable LocalDate date) {
        return assignmentService.getAssignmentsByDate(date);
    }
    @GetMapping("/staff/{staffId}")
    public List<Assignment> getAssignmentsByStaff(@PathVariable UUID staffId) {
        return assignmentService.getAssignmentsByStaff(staffId);
    }


    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody AssignmentRequest request) {
        Assignment created = assignmentService.createAssignment(
                request.getEventId(),
                request.getStaffId(),
                request.getDepartment(),
                request.getDate(),
                request.getStartTime(),
                request.getEndTime(),
                request.getAssignedById()
        );
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable UUID id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }

}
