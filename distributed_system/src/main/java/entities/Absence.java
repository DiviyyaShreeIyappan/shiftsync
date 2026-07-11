package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="absences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_id",nullable = false)
    private Staff staff;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="shift_id",nullable = false)
    private Assignment shift;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="marked_by",nullable = false)
    private Staff markedBy;

    @Column(updatable = false)
    private LocalDateTime markedAt;

    @PrePersist
    private void onCreate(){
        markedAt=LocalDateTime.now();
    }


}
