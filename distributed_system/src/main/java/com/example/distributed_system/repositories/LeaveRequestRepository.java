package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.LeaveRequest;
import com.example.distributed_system.entities.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, UUID> {
    List<LeaveRequest> findByStaffId(UUID staffId);
    List<LeaveRequest> findByStaffIdAndStartDateBetween(UUID staffId, LocalDate start,LocalDate end);
    List<LeaveRequest> findByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(LeaveStatus status, LocalDate start, LocalDate end);
    List<LeaveRequest> findByStaffIdAndStatus(UUID staffId, LeaveStatus status);
    List<LeaveRequest> findByStatus(LeaveStatus status);
    List<LeaveRequest> findByStartDateBefore(LocalDate start);
    List<LeaveRequest> findByEndDateAfter(LocalDate end);


    List<LeaveRequest> findByStaffIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            UUID staffId,
            LeaveStatus status,
            LocalDate date,
            LocalDate date2
    );
    boolean existsByStaffIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            UUID staffId,
            LeaveStatus status,
            LocalDate checkDate,
            LocalDate checkDate2
    );
}
