package entities;

import entities.enums.Department;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name="skills",
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
    private LocalDate certifiedAt;

    @PrePersist
    protected void onCreate() {
        certifiedAt = LocalDate.now();
    }
}
