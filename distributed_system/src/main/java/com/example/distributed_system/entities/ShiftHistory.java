package com.example.distributed_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="shift_history", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"staff_id", "week_start_date"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_id",nullable = false)
    private Staff staff;

    @Column(nullable = false)
    private LocalDate weekStartDate;

    @Column(nullable = false)
    private Integer shiftsCount;

    private BigDecimal totalHours;
}
