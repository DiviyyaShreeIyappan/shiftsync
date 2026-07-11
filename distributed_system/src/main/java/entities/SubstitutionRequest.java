package entities;

import entities.enums.NotificationChannel;
import entities.enums.SubstitutionResponse;
import entities.enums.SubstitutionStatus;
import lombok.*;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name="substitution_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubstitutionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="candidate_staff_id",nullable = false)
    private Staff candidateStaff;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="assignment_id",nullable = false)
    private Assignment assignment;

    @Column(nullable = false)
    private Integer rank;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubstitutionStatus status;

    @Column(updatable = false)
    private LocalDateTime contactedAt;

    @PrePersist
    protected void onCreate() {
        contactedAt = LocalDateTime.now();
    }
    private LocalDateTime respondedAt;

    @Enumerated(EnumType.STRING)
    private SubstitutionResponse response;

    @Enumerated(EnumType.STRING)
    private NotificationChannel channel;
}
