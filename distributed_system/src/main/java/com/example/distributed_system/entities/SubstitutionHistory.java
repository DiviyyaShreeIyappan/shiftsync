package com.example.distributed_system.entities;

import com.example.distributed_system.entities.enums.Department;
import com.example.distributed_system.entities.enums.SubstitutionOutcome;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name="substitution_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubstitutionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubstitutionOutcome outcome;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
