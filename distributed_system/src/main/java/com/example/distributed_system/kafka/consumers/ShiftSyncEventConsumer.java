package com.example.distributed_system.kafka.consumers;

import com.example.distributed_system.kafka.events.*;
import com.example.distributed_system.services.NotificationService;
import com.example.distributed_system.services.SubstitutionService;
import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class ShiftSyncEventConsumer {

    private final NotificationService notificationService;
    private final SubstitutionService substitutionService;

    @KafkaListener(topics = "shift_assigned", groupId = "shiftsync-group")
    public void handleShiftAssigned(String message) {
        System.out.println("Shift assigned: " + message);
    }

    @KafkaListener(topics = "conflict_detected", groupId = "shiftsync-group")
    public void handleConflictDetected(String message) {
        System.out.println("Conflict detected: " + message);
    }

    @KafkaListener(topics = "conflict_resolved", groupId = "shiftsync-group")
    public void handleConflictResolved(String message) {
        System.out.println("Conflict resolved: " + message);
    }

    @KafkaListener(topics = "staff_absent", groupId = "shiftsync-group")
    public void handleStaffAbsent(String message) {
        System.out.println("Staff absent: " + message);
    }

    @KafkaListener(topics = "substitution_requested", groupId = "shiftsync-group")
    public void handleSubstitutionRequested(String message) {
        System.out.println("Substitution requested: " + message);
    }

    @KafkaListener(topics = "substitution_confirmed", groupId = "shiftsync-group")
    public void handleSubstitutionConfirmed(String message) {
        System.out.println("Substitution confirmed: " + message);
    }

    @KafkaListener(topics = "substitution_declined", groupId = "shiftsync-group")
    public void handleSubstitutionDeclined(String message) {
        System.out.println("Substitution declined: " + message);
    }

    @KafkaListener(topics = "substitution_timeout", groupId = "shiftsync-group")
    public void handleSubstitutionTimeout(String message) {
        System.out.println("Substitution timeout: " + message);
    }

    @KafkaListener(topics = "substitution_failed", groupId = "shiftsync-group")
    public void handleSubstitutionFailed(String message) {
        System.out.println("Substitution failed: " + message);
    }

    @KafkaListener(topics = "schedule_generated", groupId = "shiftsync-group")
    public void handleScheduleGenerated(String message) {
        System.out.println("Schedule generated: " + message);
    }

    @KafkaListener(topics = "schedule_approved", groupId = "shiftsync-group")
    public void handleScheduleApproved(String message) {
        System.out.println("Schedule approved: " + message);
    }

    @KafkaListener(topics = "leave_requested", groupId = "shiftsync-group")
    public void handleLeaveRequested(String message) {
        System.out.println("Leave requested: " + message);
    }

    @KafkaListener(topics = "leave_approved", groupId = "shiftsync-group")
    public void handleLeaveApproved(String message) {
        System.out.println("Leave approved: " + message);
    }

    @KafkaListener(topics = "leave_declined", groupId = "shiftsync-group")
    public void handleLeaveDeclined(String message) {
        System.out.println("Leave declined: " + message);
    }

}
