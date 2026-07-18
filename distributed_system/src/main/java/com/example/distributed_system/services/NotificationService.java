package com.example.distributed_system.services;

import com.example.distributed_system.entities.Assignment;
import com.example.distributed_system.entities.Conflict;
import com.example.distributed_system.entities.Staff;
import com.example.distributed_system.repositories.SubstitutionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    public void sendSubstitutionRequest(Staff candidate, Assignment shift) {
        System.out.println("Sending substitution request to: " + candidate.getEmail());
        System.out.println("Shift: " + shift.getDepartment() + " on " + shift.getDate());
        // TODO: replace with real email sending
    }
    public void sendConflictAlert(Staff manager, Conflict conflict) {
        System.out.println("Sending conflict alert to: " + manager.getEmail());
        System.out.println("Conflict detected for staff: " + conflict.getStaff().getName());
        // TODO: replace with real email sending
    }

    public void sendScheduleReady(Staff manager, LocalDate weekStart) {
        System.out.println("Sending schedule ready notification to: " + manager.getEmail());
        System.out.println("Week starting: " + weekStart);
        // TODO: replace with real email sending
    }
}
