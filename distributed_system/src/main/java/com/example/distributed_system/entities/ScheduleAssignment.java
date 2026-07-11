package com.example.distributed_system.entities;

import lombok.*;
import jakarta.persistence.*;
import java.util.UUID;


@Entity
@Table(name="schedule_assignments", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_schedule_assignment",
                columnNames = {"schedule_id", "assignment_id"}
        )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="schedule_id",nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assignment_id",nullable = false)
    private Assignment assignment;
}
