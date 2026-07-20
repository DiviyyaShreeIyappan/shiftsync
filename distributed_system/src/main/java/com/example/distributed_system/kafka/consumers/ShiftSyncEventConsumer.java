package com.example.distributed_system.kafka.consumers;

import com.example.distributed_system.kafka.events.*;
import com.example.distributed_system.services.NotificationService;
import com.example.distributed_system.services.SubstitutionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShiftSyncEventConsumer {

    private final NotificationService notificationService;
    private final SubstitutionService substitutionService;

    @KafkaListener(topics="shift_assigned",groupId = "shiftsync-group")
    public void handleShiftAssigned(ShiftAssignedEvent event){
        log.info("Shift assigned: staffId={} department={} date={}",
                event.getStaffId(),event.getDepartment(),event.getDate());

    }

    @KafkaListener(topics="conflict_detected",groupId = "shiftsync-group")
    public void handleConflictDetected(ConflictDetectedEvent event){
        log.info("Conflict detected: conflictId={} staffId={}",
                event.getConflictId(),event.getStaffId());
    }

    @KafkaListener(topics = "conflict_resolved", groupId = "shiftsync-group")
    public void handleConflictResolved(ConflictResolvedEvent event) {
        log.info("Conflict resolved: conflictId={} resolvedBy={}",
                event.getConflictId(), event.getResolvedBy());
    }

    @KafkaListener(topics = "staff_absent", groupId = "shiftsync-group")
    public void handleStaffAbsent(StaffAbsentEvent event) {
        log.info("Staff absent: staffId={} shiftId={}",
                event.getStaffId(), event.getShiftId());
    }

    @KafkaListener(topics = "substitution_requested", groupId = "shiftsync-group")
    public void handleSubstitutionRequested(SubstitutionRequestedEvent event) {
        log.info("Substitution requested: candidateStaffId={} rank={}",
                event.getCandidateStaffId(), event.getRank());
    }

    @KafkaListener(topics = "substitution_confirmed", groupId = "shiftsync-group")
    public void handleSubstitutionConfirmed(SubstitutionConfirmedEvent event) {
        log.info("Substitution confirmed: assignmentId={} confirmedStaffId={}",
                event.getAssignmentId(), event.getConfirmedStaffId());
    }

    @KafkaListener(topics = "substitution_declined", groupId = "shiftsync-group")
    public void handleSubstitutionDeclined(SubstitutionDeclinedEvent event) {
        log.info("Substitution declined: assignmentId={} declinedStaffId={}",
                event.getAssignmentId(), event.getDeclinedStaffId());
    }

    @KafkaListener(topics = "substitution_timeout", groupId = "shiftsync-group")
    public void handleSubstitutionTimeout(SubstitutionTimeoutEvent event) {
        log.info("Substitution timeout: requestId={}", event.getRequestId());
    }

    @KafkaListener(topics = "substitution_failed", groupId = "shiftsync-group")
    public void handleSubstitutionFailed(SubstitutionFailedEvent event) {
        log.info("Substitution failed: assignmentId={} department={}",
                event.getAssignmentId(), event.getDepartment());
    }

    @KafkaListener(topics = "schedule_generated", groupId = "shiftsync-group")
    public void handleScheduleGenerated(ScheduleGeneratedEvent event) {
        log.info("Schedule generated: scheduleId={} weekStart={}",
                event.getScheduleId(), event.getWeekStartDate());
    }

    @KafkaListener(topics = "schedule_approved", groupId = "shiftsync-group")
    public void handleScheduleApproved(ScheduleApprovedEvent event) {
        log.info("Schedule approved: scheduleId={} approvedById={}",
                event.getScheduleId(), event.getApprovedById());
    }

    @KafkaListener(topics = "leave_requested", groupId = "shiftsync-group")
    public void handleLeaveRequested(LeaveRequestedEvent event) {
        log.info("Leave requested: staffId={} from={} to={}",
                event.getStaffId(), event.getStartDate(), event.getEndDate());
    }

    @KafkaListener(topics = "leave_approved", groupId = "shiftsync-group")
    public void handleLeaveApproved(LeaveApprovedEvent event) {
        log.info("Leave approved: leaveRequestId={} staffId={}",
                event.getLeaveRequestId(), event.getStaffId());
    }

    @KafkaListener(topics = "leave_declined", groupId = "shiftsync-group")
    public void handleLeaveDeclined(LeaveDeclinedEvent event) {
        log.info("Leave declined: leaveRequestId={} staffId={}",
                event.getLeaveRequestId(), event.getStaffId());
    }




}
