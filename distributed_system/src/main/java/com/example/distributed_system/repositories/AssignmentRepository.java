package com.example.distributed_system.repositories;


import com.example.distributed_system.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.distributed_system.entities.Assignment;
import com.example.distributed_system.entities.enums.AssignmentStatus;
import com.example.distributed_system.entities.enums.Department;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,UUID> {
    List<Assignment> findByDepartment(Department department);
    List<Assignment> findByStaffId(UUID staffId);
    List<Assignment> findByDate(LocalDate date);
    List<Assignment> findByDateAfter(LocalDate date);
    List<Assignment> findByAssignedBy(Staff assignedBy);
    List<Assignment> findByStaffIdAndDate(Staff staff,LocalDate date);
    List<Assignment> findByStatus(AssignmentStatus status);
    boolean existsByEventId(String eventId);
    List<Assignment> findByDateBetween(LocalDate start, LocalDate end);

    List<Assignment> findByStaffIdAndDateAndStartTimeLessThanAndEndTimeGreaterThanAndStatusNot(
            UUID staffId,
            LocalDate date,
            LocalTime newShiftEndTime,
            LocalTime newShiftStartTime,
            AssignmentStatus status
    );

}
