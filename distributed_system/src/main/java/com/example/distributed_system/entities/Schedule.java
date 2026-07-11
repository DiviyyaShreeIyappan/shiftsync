package com.example.distributed_system.entities;

import com.example.distributed_system.entities.enums.ScheduleStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,unique = true)
    private LocalDate weekStartDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleStatus status;

    @Column(updatable = false)
    private LocalDateTime generatedAt;

    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="approved_by")
    private Staff approvedBy;

    private LocalDateTime approvedAt;

    @OneToMany(mappedBy = "schedule")
    private List<ScheduleAssignment> scheduleAssignments= new ArrayList<>();
}
