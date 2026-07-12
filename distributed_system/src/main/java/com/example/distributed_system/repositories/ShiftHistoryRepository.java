package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.ShiftHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface ShiftHistoryRepository extends JpaRepository<ShiftHistory, UUID> {
    List<ShiftHistory> findByStaffId(UUID staffId);
    List<ShiftHistory> findByWeekStartDate(LocalDate weekStartDate);
    List<ShiftHistory> findByWeekStartDateAfter(LocalDate date);
    List<ShiftHistory> findByWeekStartDateBetween(LocalDate start,LocalDate end);
    List<ShiftHistory> findByStaffIdAndWeekStartDateAfter(UUID staffId, LocalDate weekStartDate);
    boolean existsByStaffIdAndWeekStartDate(UUID staffId, LocalDate weekStartDate);
}
