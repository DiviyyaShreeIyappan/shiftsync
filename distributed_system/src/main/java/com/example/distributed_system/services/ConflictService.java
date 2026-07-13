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
        return conflictRepository.save(conflict);
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
        return conflictRepository.save(conflict);
    }

}
