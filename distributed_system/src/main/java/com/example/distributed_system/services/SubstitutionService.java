package com.example.distributed_system.services;

import com.example.distributed_system.entities.*;
import com.example.distributed_system.entities.enums.Department;
import com.example.distributed_system.entities.enums.SubstitutionResponse;
import com.example.distributed_system.entities.enums.UnavailabilityStatus;
import com.example.distributed_system.repositories.*;
import com.example.distributed_system.entities.enums.SubstitutionOutcome;
import com.example.distributed_system.entities.enums.SubstitutionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubstitutionService {
    private final StaffSkillRepository staffSkillRepository;
    private final SubstitutionRequestRepository substitutionRequestRepository;
    private final SubstitutionHistoryRepository substitutionHistoryRepository;
    private final AssignmentRepository assignmentRepository;
    private final UnavailabilityService unavailabilityService;
    private final NotificationService notificationService;

    void initiateSubstitution(Assignment shift){
        Department department=shift.getDepartment();
        List<StaffSkill> skilledStaff=staffSkillRepository.findByDepartment(department);
        List<Staff> candidates=skilledStaff.stream()
                .map(StaffSkill::getStaff)
                .collect(Collectors.toList());
        candidates.removeIf(staff -> staff.getId().equals(shift.getStaff().getId()));
        candidates.removeIf(staff -> {
            UnavailabilityStatus status = unavailabilityService
                    .getUnavailabilityStatus(staff.getId(), shift.getDate());
            return status == UnavailabilityStatus.EXCLUDED ||
                    status == UnavailabilityStatus.ABSENT;
        });
        if (candidates.isEmpty()) {
            System.out.println("No eligible candidates found for shift: " + shift.getId());
            return;
        }

// sort by fewest recent substitutions — fairness
        candidates.sort((a, b) -> {
            long aCount = substitutionHistoryRepository
                    .countByStaffIdAndOutcomeAndDateAfter(
                            a.getId(),
                            SubstitutionOutcome.CONFIRMED,
                            shift.getDate().minusWeeks(4));
            long bCount = substitutionHistoryRepository
                    .countByStaffIdAndOutcomeAndDateAfter(
                            b.getId(),
                            SubstitutionOutcome.CONFIRMED,
                            shift.getDate().minusWeeks(4));
            return Long.compare(aCount, bCount);
        });

// take top candidate
        Staff topCandidate = candidates.get(0);

// create substitution request
        SubstitutionRequest request = SubstitutionRequest.builder()
                .assignment(shift)
                .candidateStaff(topCandidate)
                .rank(1)
                .status(SubstitutionStatus.PENDING)
                .build();

        substitutionRequestRepository.save(request);


        notificationService.sendSubstitutionRequest(topCandidate, shift);
    }

    void handleResponse(UUID requestId, SubstitutionResponse response){
//        find the request
        SubstitutionRequest request = substitutionRequestRepository
                .findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found: " + requestId));
        request.setRespondedAt(LocalDateTime.now());
        request.setResponse(response);
        if(response==SubstitutionResponse.YES){
            request.setStatus(SubstitutionStatus.CONFIRMED);
            Assignment assignment=request.getAssignment();
            assignment.setStaff(request.getCandidateStaff());
            assignmentRepository.save(assignment);
            SubstitutionHistory history=SubstitutionHistory.builder()
                    .staff(request.getCandidateStaff())
                    .date(request.getAssignment().getDate())
                    .department(request.getAssignment().getDepartment())
                    .outcome(SubstitutionOutcome.CONFIRMED)
                    .build();
            substitutionHistoryRepository.save(history);
        }
        else{
            request.setStatus(SubstitutionStatus.DECLINED);
            SubstitutionHistory history=SubstitutionHistory.builder()
                    .staff(request.getCandidateStaff())
                    .date(request.getAssignment().getDate())
                    .department(request.getAssignment().getDepartment())
                    .outcome(SubstitutionOutcome.DECLINED)
                    .build();
            substitutionHistoryRepository.save(history);
            Assignment assignment = request.getAssignment();
            List<SubstitutionRequest> allRequests = substitutionRequestRepository
                    .findByAssignmentIdOrderByRankAsc(assignment.getId());
            Optional<SubstitutionRequest> nextRequest = allRequests.stream()
                    .filter(r -> r.getRank() > request.getRank())
                    .findFirst();
            if (nextRequest.isPresent()) {
                notificationService.sendSubstitutionRequest(
                        nextRequest.get().getCandidateStaff(),
                        assignment
                );
            } else {
                // no more candidates — notify manager
                System.out.println("No more candidates for shift: " + assignment.getId());
                // TODO: notify manager when NotificationService is fully implemented
            }
        }
        substitutionRequestRepository.save(request);
    }


    void handleTimeout(UUID requestId){
        SubstitutionRequest request = substitutionRequestRepository
                .findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found: " + requestId));
        request.setStatus(SubstitutionStatus.TIMEOUT);
        request.setRespondedAt(LocalDateTime.now());
        request.setResponse(null);
        SubstitutionHistory history = SubstitutionHistory.builder()
                .staff(request.getCandidateStaff())
                .date(request.getAssignment().getDate())
                .department(request.getAssignment().getDepartment())
                .outcome(SubstitutionOutcome.TIMEOUT)
                .build();
        substitutionHistoryRepository.save(history);
        Assignment assignment = request.getAssignment();
        List<SubstitutionRequest> allRequests = substitutionRequestRepository
                .findByAssignmentIdOrderByRankAsc(assignment.getId());
        Optional<SubstitutionRequest> nextRequest = allRequests.stream()
                .filter(r -> r.getRank() > request.getRank())
                .findFirst();

        if (nextRequest.isPresent()) {
            notificationService.sendSubstitutionRequest(
                    nextRequest.get().getCandidateStaff(),
                    assignment
            );
        } else {
            System.out.println("No more candidates for shift: " + assignment.getId());
        }

        substitutionRequestRepository.save(request);
    }
    
    SubstitutionRequest getSubstitutionStatus(UUID assignmentId){
        return substitutionRequestRepository
                .findByAssignmentIdOrderByRankAsc(assignmentId)
                .stream()
                .filter(r -> r.getStatus() == SubstitutionStatus.PENDING)
                .findFirst()
                .orElse(null);
    }

}
