package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.ScheduleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleAssignmentRepository extends JpaRepository<ScheduleAssignment, UUID> {
    List<ScheduleAssignment> findByScheduleId(UUID scheduleId);
    List<ScheduleAssignment> findByAssignmentId(UUID assignment);
    boolean  existsByScheduleIdAndAssignmentId(UUID scheduleId, UUID assignmentId);
    void deleteByScheduleId(UUID scheduleId);
}
