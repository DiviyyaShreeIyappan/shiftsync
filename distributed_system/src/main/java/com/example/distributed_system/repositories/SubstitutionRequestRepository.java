package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.SubstitutionRequest;
import com.example.distributed_system.entities.enums.SubstitutionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubstitutionRequestRepository extends JpaRepository<SubstitutionRequest, UUID> {
    List<SubstitutionRequest> findByRank(Integer rank);
    List<SubstitutionRequest> findByCandidateStaffId(UUID staffId);
    List<SubstitutionRequest> findByAssignmentId(UUID assignment);
    List<SubstitutionRequest> findByStatus(SubstitutionStatus status);
    List<SubstitutionRequest> findByAssignmentIdAndStatus(
            UUID assignmentId, SubstitutionStatus status);
    // find all requests for an assignment ordered by rank
    List<SubstitutionRequest> findByAssignmentIdOrderByRankAsc(UUID assignmentId);
    boolean existsByCandidateStaffIdAndAssignmentId(UUID staffId, UUID assignmentId);
    List<SubstitutionRequest> findByAssignmentIdAndStatusOrderByRankAsc(
            UUID assignmentId, SubstitutionStatus status);
}
