package com.example.distributed_system.services;

import com.example.distributed_system.entities.Absence;
import com.example.distributed_system.entities.LeaveRequest;
import com.example.distributed_system.entities.UnavailabilityFlag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UnavailabilityService {
    private final UnavailabilityFlag unavailabilityService;
    private final LeaveRequest leaveRequest;
    private final Absence absence;

    @Transactional(readOnly = true)
    
}
