package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.Conflict;
import com.example.distributed_system.entities.enums.ConflictResolvedBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, UUID> {
    List<Conflict> findByStaffId(UUID staff);
    List<Conflict> findByAssignmentId1Id(UUID assignment);
    List<Conflict> findByAssignmentId2Id(UUID assignment);
    List<Conflict> findByWinningAssignmentIsNotNull();
    List<Conflict> findByStaffIdAndResolvedAtIsNull(UUID staffId);
    List<Conflict> findByResolvedBy(ConflictResolvedBy resolvedBy);
    List<Conflict> findByResolvedAtIsNull();
}
