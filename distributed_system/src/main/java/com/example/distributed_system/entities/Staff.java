package com.example.distributed_system.entities;

import com.example.distributed_system.entities.enums.ContractType;
import com.example.distributed_system.entities.enums.StaffRole;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractType contractType;

    private Integer contractedHours;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "staff")
    private List<StaffSkill> skills= new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<UnavailabilityFlag> unavailability= new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<Absence> absences=new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<ShiftHistory> shiftHistories=new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<LeaveRequest> leaveRequests=new ArrayList<>();

    @OneToMany(mappedBy="staff")
    private List<Conflict> conflicts=new ArrayList<>();

    @OneToMany(mappedBy = "candidateStaff")
    private List<SubstitutionRequest> substitutionRequests=new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<SubstitutionHistory> substitutionHistories=new ArrayList<>();
}