package entities;

import entities.enums.ConflictResolvedBy;
import lombok.*;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name="conflicts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conflict {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="staff_id",nullable = false)
    private Staff staff;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="assignment_id_1",nullable = false)
    private Assignment assignmentId1;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="assignment_id_2",nullable = false)
    private Assignment assignmentId2;

    @Column(updatable = false)
    private LocalDateTime detectedAt;

    @PrePersist
    protected void onCreate() {
        detectedAt = LocalDateTime.now();
    }

    private LocalDateTime resolvedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winning_assignment_id")
    private Assignment winningAssignment;

    @Enumerated(EnumType.STRING)
    private ConflictResolvedBy resolvedBy;

    @Column(columnDefinition = "TEXT")
    private String resolutionReason;
}
