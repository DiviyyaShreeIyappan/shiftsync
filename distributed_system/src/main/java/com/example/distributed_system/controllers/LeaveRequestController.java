package com.example.distributed_system.controllers;

import com.example.distributed_system.entities.LeaveRequest;
import com.example.distributed_system.repositories.LeaveRequestRepository;
import com.example.distributed_system.repositories.StaffRepository;
import com.example.distributed_system.entities.enums.LeaveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/leave")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestRepository leaveRequestRepository;
    private final StaffRepository staffRepository;

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<LeaveRequest> createLeaveRequest(
            @RequestParam UUID staffId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam String reason) {

        LeaveRequest request = LeaveRequest.builder()
                .staff(staffRepository.findById(staffId).orElseThrow())
                .startDate(startDate)
                .endDate(endDate)
                .reason(reason)
                .status(LeaveStatus.PENDING)
                .build();

        return ResponseEntity.status(201).body(leaveRequestRepository.save(request));
    }

    @PatchMapping("/{id}/approve")
    public LeaveRequest approveLeave(
            @PathVariable UUID id,
            @RequestParam UUID managerId) {

        LeaveRequest request = leaveRequestRepository.findById(id).orElseThrow();
        request.setStatus(LeaveStatus.APPROVED);
        request.setApprovedBy(staffRepository.findById(managerId).orElseThrow());
        return leaveRequestRepository.save(request);
    }

    @PatchMapping("/{id}/decline")
    public LeaveRequest declineLeave(@PathVariable UUID id) {
        LeaveRequest request = leaveRequestRepository.findById(id).orElseThrow();
        request.setStatus(LeaveStatus.DECLINED);
        return leaveRequestRepository.save(request);
    }
}
