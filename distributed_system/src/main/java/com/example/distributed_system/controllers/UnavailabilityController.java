package com.example.distributed_system.controllers;

import com.example.distributed_system.entities.Absence;
import com.example.distributed_system.entities.LeaveRequest;
import com.example.distributed_system.entities.UnavailabilityFlag;
import com.example.distributed_system.entities.enums.LeaveStatus;
import com.example.distributed_system.entities.enums.UnavailabilityFlagStatus;
import com.example.distributed_system.entities.enums.UnavailabilityStatus;
import com.example.distributed_system.services.UnavailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/unavailability")
@RequiredArgsConstructor
public class UnavailabilityController {

    private final UnavailabilityService unavailabilityService;

    @GetMapping("/status")
    public UnavailabilityStatus getStatus(
            @RequestParam UUID staffId,
            @RequestParam LocalDate date) {
        return unavailabilityService.getUnavailabilityStatus(staffId, date);
    }

    @GetMapping("/flags")
    public List<UnavailabilityFlag> getFlagsByStatusAndDate(
            @RequestParam UnavailabilityFlagStatus status,
            @RequestParam LocalDate date) {
        return unavailabilityService.getUnavailabilityByStatusAndDate(status, date);
    }

    @GetMapping("/leave")
    public List<LeaveRequest> getApprovedLeave(
            @RequestParam LocalDate date) {
        return unavailabilityService.getApprovedLeave(date);
    }

    @GetMapping("/absences")
    public List<Absence> getAbsencesByDate(
            @RequestParam LocalDate date) {
        return unavailabilityService.getAbsenceByDate(date);
    }
}