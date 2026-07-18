package com.example.distributed_system.controllers;

import com.example.distributed_system.entities.Schedule;
import com.example.distributed_system.entities.enums.Department;
import com.example.distributed_system.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/generate")
    public ResponseEntity<Schedule> generateSchedule(
            @RequestParam Department department,
            @RequestParam LocalDate weekStart,
            @RequestParam UUID managerId) {
        return ResponseEntity.status(201)
                .body(scheduleService.generateDepartmentSchedule(department, weekStart, managerId));
    }

    @PutMapping("/{scheduleId}/approve")
    public Schedule approveSchedule(
            @PathVariable UUID scheduleId,
            @RequestParam UUID managerId) {
        return scheduleService.approveSchedule(scheduleId, managerId);
    }

    @GetMapping("/week/{weekStart}")
    public Schedule getScheduleForWeek(@PathVariable LocalDate weekStart) {
        return scheduleService.getScheduleForWeek(weekStart);
    }

    @GetMapping("/drafts")
    public List<Schedule> getDraftSchedules() {
        return scheduleService.getDraftSchedules();
    }
}