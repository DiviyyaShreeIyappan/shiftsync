package entities;

import entities.enums.Department;
import entities.enums.UnavailabilityFlagStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "unavailability_flags",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_staff_date",
                        columnNames = {"staff_id", "date"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnavailabilityFlag{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id",nullable = false)
    private Staff staff;

    @Column(nullable = false)
    private LocalDate date;


    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnavailabilityFlagStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="override_by",nullable = false)
    private Staff overrideBy;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate(){
         createdAt=LocalDateTime.now();
    }

}
