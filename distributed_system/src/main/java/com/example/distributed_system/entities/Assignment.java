package com.example.distributed_system.entities;

import com.example.distributed_system.entities.enums.AssignmentStatus;
import com.example.distributed_system.entities.enums.Department;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name= "assignments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_staff_shift_window",
                        columnNames = {"staff_id", "date", "start_time", "end_time"}
                )
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="staff_id",nullable = false)
    private Staff staff;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="assigned_by",nullable = false)
    private Staff assignedBy;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate(){
        createdAt= LocalDateTime.now();
    }

    @Column(unique = true, updatable = false)
    private String eventId;

    @OneToMany(mappedBy = "shift")
    private List<Absence> absences=new ArrayList<>();

    @OneToMany(mappedBy = "assignment")
    private List<ScheduleAssignment> scheduleAssignments=new ArrayList<>();

    @OneToMany(mappedBy = "assignmentId1")
    private List<Conflict> conflictsAsFirst=new ArrayList<>();


    @OneToMany(mappedBy = "assignmentId2")
    private List<Conflict> conflictsAsSecond=new ArrayList<>();

    @OneToMany(mappedBy = "winningAssignment")
    private List<Conflict> winningAssignments=new ArrayList<>();

    @OneToMany(mappedBy = "assignment")
    private List<SubstitutionRequest> substitutionRequests=new ArrayList<>();
}
