package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.UnavailabilityFlag;
import com.example.distributed_system.entities.enums.UnavailabilityFlagStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UnavailabilityFlagRepository extends JpaRepository<UnavailabilityFlag, UUID> {

    List<UnavailabilityFlag> findByStatusNot(UnavailabilityFlagStatus status);
    List<UnavailabilityFlag> findByStatusAndDate(UnavailabilityFlagStatus status, LocalDate date);
    List<UnavailabilityFlag> findByStatus(UnavailabilityFlagStatus status);
    List<UnavailabilityFlag> findByStatusAndDateAfter(UnavailabilityFlagStatus status, LocalDate date);
    List<UnavailabilityFlag> findByStatusAndDateBetween(UnavailabilityFlagStatus status, LocalDate start,LocalDate end);
    List<UnavailabilityFlag> findByStaffId(UUID staffId);
    boolean existsByStaffIdAndDate(UUID staffId, LocalDate date);
    Optional<UnavailabilityFlag> findByStaffIdAndDate(UUID staffId, LocalDate date);

}
