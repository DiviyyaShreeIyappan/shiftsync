package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.Schedule;
import com.example.distributed_system.entities.enums.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    List<Schedule> findByApprovedById(UUID staffId);

    List<Schedule> findByStatus(ScheduleStatus status);

    Optional<Schedule> findByWeekStartDate(LocalDate date);

    List<Schedule> findByWeekStartDateAfter(LocalDate date);
    boolean existsByWeekStartDate(LocalDate weekStartDate);
    List<Schedule> findByApprovedAtIsNotNull();
    List<Schedule> findByApprovedAtAfter(LocalDateTime time);
    List<Schedule> findByWeekStartDateBetween(LocalDate start, LocalDate end);
    Optional<Schedule> findTopByOrderByWeekStartDateDesc();
}

