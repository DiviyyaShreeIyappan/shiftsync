package com.example.distributed_system.kafka.producers;

import com.example.distributed_system.kafka.events.*;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShiftSyncEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    // Assignment events
    public void publishShiftAssigned(ShiftAssignedEvent event) {
        kafkaTemplate.send("shift_assigned", event.getEventId(), event);
    }

    public void publishShiftCancelled(ShiftCancelledEvent event) {
        kafkaTemplate.send("shift_cancelled", event.getEventId(), event);
    }

    // Conflict events
    public void publishConflictDetected(ConflictDetectedEvent event) {
        kafkaTemplate.send("conflict_detected", event.getEventId(), event);
    }

    public void publishConflictResolved(ConflictResolvedEvent event) {
        kafkaTemplate.send("conflict_resolved", event.getEventId(), event);
    }

    // Absence events
    public void publishStaffAbsent(StaffAbsentEvent event) {
        kafkaTemplate.send("staff_absent", event.getEventId(), event);
    }

    // Substitution events
    public void publishSubstitutionRequested(SubstitutionRequestedEvent event) {
        kafkaTemplate.send("substitution_requested", event.getEventId(), event);
    }

    public void publishSubstitutionConfirmed(SubstitutionConfirmedEvent event) {
        kafkaTemplate.send("substitution_confirmed", event.getEventId(), event);
    }

    public void publishSubstitutionDeclined(SubstitutionDeclinedEvent event) {
        kafkaTemplate.send("substitution_declined", event.getEventId(), event);
    }

    public void publishSubstitutionTimeout(SubstitutionTimeoutEvent event) {
        kafkaTemplate.send("substitution_timeout", event.getEventId(), event);
    }

    public void publishSubstitutionFailed(SubstitutionFailedEvent event) {
        kafkaTemplate.send("substitution_failed", event.getEventId(), event);
    }

    // Schedule events
    public void publishScheduleGenerated(ScheduleGeneratedEvent event) {
        kafkaTemplate.send("schedule_generated", event.getEventId(), event);
    }

    public void publishScheduleApproved(ScheduleApprovedEvent event) {
        kafkaTemplate.send("schedule_approved", event.getEventId(), event);
    }

    // Leave events
    public void publishLeaveRequested(LeaveRequestedEvent event) {
        kafkaTemplate.send("leave_requested", event.getEventId(), event);
    }

    public void publishLeaveApproved(LeaveApprovedEvent event) {
        kafkaTemplate.send("leave_approved", event.getEventId(), event);
    }

    public void publishLeaveDeclined(LeaveDeclinedEvent event) {
        kafkaTemplate.send("leave_declined", event.getEventId(), event);
    }
}
