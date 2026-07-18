package com.example.distributed_system.services;

import com.example.distributed_system.entities.*;
import com.example.distributed_system.entities.enums.*;
import com.example.distributed_system.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleAssignmentRepository scheduleAssignmentRepository;
    private final ShiftTemplateRepository shiftTemplateRepository;
    private final StaffRepository staffRepository;
    private final StaffSkillRepository staffSkillRepository;
    private final AssignmentRepository assignmentRepository;
    private final ShiftHistoryRepository shiftHistoryRepository;
    private final UnavailabilityService unavailabilityService;
    private final NotificationService notificationService;
    // generate schedule for one department for one week
    public Schedule generateDepartmentSchedule(Department department, LocalDate weekStart, UUID managerId){
        Schedule schedule=scheduleRepository.findByWeekStartDate(weekStart)
                .orElse(Schedule.builder()
                        .weekStartDate(weekStart)
                        .status(ScheduleStatus.DRAFT)
                        .build());
        scheduleRepository.save(schedule);
        Staff manager = staffRepository.findById(managerId).orElseThrow();
        for(int i=0;i<7;i++){
            LocalDate currentDay=weekStart.plusDays(i);
            String dayName=currentDay.getDayOfWeek().name();
            List<ShiftTemplate> templates=shiftTemplateRepository
                    .findByDepartmentAndActive(department,true)
                    .stream()
                    .filter(t->t.getApplicableDays().contains(dayName))
                    .collect(Collectors.toList());
            // correct — fetch once before all loops

            for(ShiftTemplate template:templates){
                List<Staff> skilledStaff=staffSkillRepository
                        .findByDepartment(department)
                        .stream()
                        .map(StaffSkill::getStaff)
                        .collect(Collectors.toList());
                skilledStaff.removeIf(staff ->{
                    UnavailabilityStatus status =unavailabilityService.getUnavailabilityStatus(staff.getId(),currentDay);
                    return status == UnavailabilityStatus.EXCLUDED ||
                            status == UnavailabilityStatus.ABSENT;
                });
                skilledStaff.removeIf(staff -> !assignmentRepository
                        .findByStaffIdAndDateAndStartTimeLessThanAndEndTimeGreaterThanAndStatusNot(
                                staff.getId(),
                                currentDay,
                                template.getEndTime(),
                                template.getStartTime(),
                                AssignmentStatus.CANCELLED
                        ).isEmpty());
                List<Staff> fixedStaff=skilledStaff.stream()
                        .filter(s->s.getContractType()== ContractType.FIXED)
                        .collect(Collectors.toList());
                List<Staff> flexibleStaff=skilledStaff.stream()
                        .filter(s->s.getContractType()==ContractType.FLEXIBLE)
                        .collect(Collectors.toList());

                LocalDate fourWeeksAgo =currentDay.minusWeeks(4);
                flexibleStaff.sort((a,b) ->{
                    long aHours=shiftHistoryRepository
                            .findByStaffIdAndWeekStartDateAfter(a.getId(),fourWeeksAgo)
                            .stream()
                            .mapToLong(h->h.getTotalHours().longValue())
                            .sum();
                    long bHours = shiftHistoryRepository
                            .findByStaffIdAndWeekStartDateAfter(b.getId(), fourWeeksAgo)
                            .stream()
                            .mapToLong(h -> h.getTotalHours().longValue())
                            .sum();
                    return Long.compare(aHours, bHours);
                });
                List<Staff> rankedCandidates = new ArrayList<>();
                rankedCandidates.addAll(fixedStaff);
                rankedCandidates.addAll(flexibleStaff);

                int assigned=0;
                for(Staff candidate:rankedCandidates){
                    if(assigned>=template.getStaffRequired()) break;


                    Assignment assignment= Assignment.builder()
                            .staff(candidate)
                            .department(department)
                            .date(currentDay)
                            .startTime(template.getStartTime())
                            .endTime(template.getEndTime())
                            .status(AssignmentStatus.CONFIRMED)
                            .assignedBy(manager)
                            .eventId(UUID.randomUUID().toString())
                            .build();
                    Assignment saved = assignmentRepository.save(assignment);
                    ScheduleAssignment scheduleAssignment = ScheduleAssignment.builder()
                            .schedule(schedule)
                            .assignment(saved)
                            .build();
                    scheduleAssignmentRepository.save(scheduleAssignment);

                    assigned++;

                }
                // notify manager schedule is ready

                notificationService.sendScheduleReady(manager, weekStart);
                schedule = scheduleRepository.save(schedule);


            }

        }
        return schedule;
    }

    // approve a draft schedule
    public Schedule approveSchedule(UUID scheduleId, UUID managerId){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found: " + scheduleId));
        Staff manager = staffRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found: " + managerId));
        schedule.setStatus(ScheduleStatus.APPROVED);
        schedule.setApprovedBy(manager);
        schedule.setApprovedAt(LocalDateTime.now());

        return scheduleRepository.save(schedule);
    }

    // get schedule for a week
    @Transactional(readOnly = true)
    public Schedule getScheduleForWeek(LocalDate weekStart){
        return scheduleRepository.findByWeekStartDate(weekStart)
                .orElseThrow(()->new RuntimeException("No schedule found for week: "+weekStart));
    }

    // get all draft schedules
    @Transactional(readOnly = true)
    public List<Schedule> getDraftSchedules(){
        return scheduleRepository.findByStatus(ScheduleStatus.DRAFT);
    }
}
