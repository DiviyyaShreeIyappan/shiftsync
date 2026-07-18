package com.example.distributed_system.services;

//detect conflict
//log conflict
//resolve conflict
//notify both managers

import com.example.distributed_system.entities.Assignment;
import com.example.distributed_system.entities.Conflict;
import com.example.distributed_system.entities.Staff;
import com.example.distributed_system.entities.enums.AssignmentStatus;
import com.example.distributed_system.entities.enums.ConflictResolvedBy;
import com.example.distributed_system.kafka.events.ConflictDetectedEvent;
import com.example.distributed_system.kafka.events.ConflictResolvedEvent;
import com.example.distributed_system.kafka.producers.ShiftSyncEventProducer;
import com.example.distributed_system.repositories.AssignmentRepository;
import com.example.distributed_system.repositories.ConflictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ConflictService {
    private final ConflictRepository conflictRepository;
    private final AssignmentRepository assignmentRepository;
    private final ShiftSyncEventProducer eventProducer;
    @Transactional(readOnly = true)
    public List<Conflict> getUnresolvedConflicts(){
        return conflictRepository.findByResolvedAtIsNull();
    }
    @Transactional(readOnly = true)
    public List<Conflict> getConflictsByStaffId(UUID staffId){
        return conflictRepository.findByStaffId(staffId);
    }
    public Conflict detectConflict(Staff staff, Assignment assignment1, Assignment assignment2){
        Conflict conflict=Conflict.builder()
                .staff(staff)
                .assignmentId1(assignment1)
                .assignmentId2(assignment2)
                .build();
        Conflict saved = conflictRepository.save(conflict);
        ConflictDetectedEvent event = ConflictDetectedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .conflictId(saved.getId())
                .staffId(staff.getId())
                .assignmentId1(assignment1.getId())
                .assignmentId2(assignment2.getId())
                .detectedAt(LocalDateTime.now())
                .build();

        eventProducer.publishConflictDetected(event);
        return saved;
    }
    public Conflict resolveConflict(UUID conflictId, Assignment winningAssignment, ConflictResolvedBy resolvedBy, String resolutionReason){
        Conflict conflict=conflictRepository.findById(conflictId).orElseThrow(()->new RuntimeException("Not found: "+conflictId));
        conflict.setWinningAssignment(winningAssignment);
        if(conflict.getAssignmentId1().equals(winningAssignment)){
                conflict.getAssignmentId2().setStatus(AssignmentStatus.CANCELLED);
                 assignmentRepository.save(conflict.getAssignmentId2());
        }
        else{
            conflict.getAssignmentId1().setStatus(AssignmentStatus.CANCELLED);
            assignmentRepository.save(conflict.getAssignmentId1());
        }
        if(winningAssignment!=null){
            conflict.setResolvedAt(LocalDateTime.now());
            conflict.setResolvedBy(resolvedBy);
            conflict.setResolutionReason(resolutionReason);
        }
        Conflict resolved = conflictRepository.save(conflict);

        // publish Kafka event
        ConflictResolvedEvent event = ConflictResolvedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .conflictId(resolved.getId())
                .winningAssignmentId(winningAssignment.getId())
                .resolvedBy(resolvedBy.name())
                .resolutionReason(resolutionReason)
                .build();

        eventProducer.publishConflictResolved(event);
        return resolved;
    }

}
