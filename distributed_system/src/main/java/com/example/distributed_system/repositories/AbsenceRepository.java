package com.example.distributed_system.repositories;

import com.example.distributed_system.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, UUID> {
    List<Absence> findByStaffId(UUID staffId);
    List<Absence> findByStaffIdAndDate(UUID staffId, LocalDate date);
    List<Absence> findByStaffIdAndShiftId(UUID staffId, UUID shiftId);
    boolean existsByStaffIdAndDate(UUID staffId, LocalDate date);
    List<Absence> findByDate(LocalDate date);
    List<Absence> findByShiftId(UUID shiftId);
}
