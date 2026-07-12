package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.SubstitutionHistory;
import com.example.distributed_system.entities.enums.Department;
import com.example.distributed_system.entities.enums.SubstitutionOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface SubstitutionHistoryRepository extends JpaRepository<SubstitutionHistory, UUID> {
    List<SubstitutionHistory> findByStaffId(UUID staff);
    List<SubstitutionHistory> findByDate(LocalDate date);
    List<SubstitutionHistory> findByDepartment(Department department);
    List<SubstitutionHistory> findByOutcome(SubstitutionOutcome outcome);
    List<SubstitutionHistory> findByStaffIdAndDateAfter(UUID staffId, LocalDate date);
    List<SubstitutionHistory> findByStaffIdAndDepartmentAndDateAfter(
            UUID staffId, Department department, LocalDate date);
    long countByStaffIdAndOutcomeAndDateAfter(
            UUID staffId, SubstitutionOutcome outcome, LocalDate date);
}
