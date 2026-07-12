package com.example.distributed_system.services;

import com.example.distributed_system.entities.Absence;
import com.example.distributed_system.entities.LeaveRequest;
import com.example.distributed_system.entities.UnavailabilityFlag;
import com.example.distributed_system.entities.enums.LeaveStatus;
import com.example.distributed_system.entities.enums.UnavailabilityFlagStatus;
import com.example.distributed_system.entities.enums.UnavailabilityStatus;
import com.example.distributed_system.repositories.AbsenceRepository;
import com.example.distributed_system.repositories.LeaveRequestRepository;
import com.example.distributed_system.repositories.UnavailabilityFlagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UnavailabilityService {
    private final UnavailabilityFlagRepository unavailabilityFlagRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final AbsenceRepository absenceRepository;

    @Transactional(readOnly = true)
    public List<UnavailabilityFlag> getUnavailabilityByStatusAndDate(UnavailabilityFlagStatus status, LocalDate date){
        return unavailabilityFlagRepository.findByStatusAndDate(status,date);
    }

    @Transactional(readOnly = true)
    public List<LeaveRequest> getApprovedLeave(LocalDate date){
        return leaveRequestRepository.findByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(LeaveStatus.APPROVED,date,date);
    }

    @Transactional(readOnly = true)
    public List<Absence> getAbsenceByDate(LocalDate date){
        return absenceRepository.findByDate(date);
    }

    @Transactional(readOnly = true)
    public UnavailabilityStatus getUnavailabilityStatus(UUID staffId, LocalDate date) {
        if (absenceRepository.existsByStaffIdAndDate(staffId, date)) {
            return UnavailabilityStatus.ABSENT;
        }
        if (leaveRequestRepository
                .existsByStaffIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        staffId, LeaveStatus.APPROVED, date, date)) {
            return UnavailabilityStatus.EXCLUDED;
        }
        if (unavailabilityFlagRepository.existsByStaffIdAndDate(staffId, date)) {
            return UnavailabilityStatus.FLAGGED;
        }
        return UnavailabilityStatus.AVAILABLE;
    }
}
