package com.example.distributed_system.entities;

import com.example.distributed_system.entities.enums.Department;
import jakarta.persistence.*;

import lombok.*;


import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "shift_templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Integer staffRequired;

    @Column(nullable = false)
    private String applicableDays;

    @Column(nullable = false)
    private Boolean active;
}
