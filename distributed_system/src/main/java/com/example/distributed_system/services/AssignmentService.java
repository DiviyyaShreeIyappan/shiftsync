package com.example.distributed_system.services;

import com.example.distributed_system.entities.Assignment;
import com.example.distributed_system.entities.ShiftHistory;
import com.example.distributed_system.entities.Staff;
import com.example.distributed_system.entities.enums.AssignmentStatus;
import com.example.distributed_system.entities.enums.Department;
import com.example.distributed_system.entities.enums.UnavailabilityStatus;
import com.example.distributed_system.kafka.events.ShiftAssignedEvent;
import com.example.distributed_system.kafka.producers.ShiftSyncEventProducer;
import com.example.distributed_system.repositories.AssignmentRepository;
import com.example.distributed_system.repositories.ShiftHistoryRepository;
import com.example.distributed_system.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final StaffRepository staffRepository;
    private final ShiftHistoryRepository shiftHistoryRepository;
    private final UnavailabilityService unavailabilityService;
    private final ShiftSyncEventProducer eventProducer;

    @Transactional(readOnly = true)
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public void deleteAssignment(UUID id) {
        if (!assignmentRepository.existsById(id)) {
            throw new RuntimeException("Assignment not found: " + id);
        }
        assignmentRepository.deleteById(id);
    }
    public Assignment createAssignment(String eventId, UUID staffId, Department department, LocalDate date, LocalTime startTime, LocalTime endTime, UUID assignedById) {
        if (assignmentRepository.existsByEventId(eventId)) {
            return null;
        }
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not found for :" + staffId));
        UnavailabilityStatus status = unavailabilityService.getUnavailabilityStatus(staffId, date);
        if (status == UnavailabilityStatus.EXCLUDED || status == UnavailabilityStatus.ABSENT) {
            throw new RuntimeException("Staff is unavailable on " + date + " - Status: " + status);
        }
        List<Assignment> overlapping = assignmentRepository.findByStaffIdAndDateAndStartTimeLessThanAndEndTimeGreaterThanAndStatusNot(staffId,
                date,
                endTime,
                startTime,
                AssignmentStatus.CANCELLED);
        if (!overlapping.isEmpty()) {
            throw new RuntimeException(
                    "Staff already has an overlapping shift on " + date +
                            " between " + startTime + " and " + endTime
            );
        }
        Assignment assignment = Assignment.builder()
                .staff(staff)
                .department(department)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .status(AssignmentStatus.CONFIRMED)
                .assignedBy(staffRepository.findById(assignedById).orElse(null))
                .eventId(eventId)
                .build();
        Assignment saved = assignmentRepository.save(assignment);
        // publish Kafka event
        ShiftAssignedEvent event = ShiftAssignedEvent.builder()
                .eventId(eventId)
                .assignmentId(saved.getId())
                .staffId(staffId)
                .assignedById(assignedById)
                .department(department.name())
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        eventProducer.publishShiftAssigned(event);

// Step 6 — update shift history
        updateShiftHistory(staffId, date);

        return saved;
    }
    @Transactional(readOnly = true)
    public List<Assignment> getAssignmentsByDate(LocalDate date) {
        return assignmentRepository.findByDate(date);
    }

    @Transactional(readOnly = true)
    public List<Assignment> getAssignmentsByStaff(UUID staffId) {
        return assignmentRepository.findByStaffId(staffId);
    }

    @Transactional(readOnly = true)
    public Assignment getAssignmentById(UUID id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found: " + id));
    }
    private void updateShiftHistory(UUID staffId, LocalDate date) {
        LocalDate weekStart = date.with(java.time.DayOfWeek.MONDAY);

        if (shiftHistoryRepository.existsByStaffIdAndWeekStartDate(staffId, weekStart)) {
            ShiftHistory history = shiftHistoryRepository
                    .findByStaffIdAndWeekStartDate(staffId, weekStart);
            history.setShiftsCount(history.getShiftsCount() + 1);
            shiftHistoryRepository.save(history);
        } else {
            Staff staff = staffRepository.findById(staffId).orElseThrow();
            ShiftHistory newHistory = ShiftHistory.builder()
                    .staff(staff)
                    .weekStartDate(weekStart)
                    .shiftsCount(1)
                    .build();
            shiftHistoryRepository.save(newHistory);
        }
    }
}


