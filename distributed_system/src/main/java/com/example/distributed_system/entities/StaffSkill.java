package com.example.distributed_system.entities;

import com.example.distributed_system.entities.enums.Department;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name="staff_skills",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"staff_id","department"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffSkill{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(updatable = false)
    private LocalDateTime certifiedAt;

    @PrePersist
    protected void onCreate() {
        certifiedAt = LocalDateTime.now();
    }
}
