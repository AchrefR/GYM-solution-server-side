package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A dated personal-best for a single lift (e.g. "Bench Press" at 80kg on a date),
 * used to render core-lift evolution charts over time.
 */
@Entity
@Table(name = "strength_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StrengthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long strengthRecordId;

    /** The exercise name, e.g. "Bench Press", "Squat", "Deadlift". */
    private String exercise;

    /** Weight lifted, in kilograms. */
    private Double weight;

    /** Optional repetitions performed at that weight. */
    private Integer reps;

    /** ISO date string (yyyy-MM-dd) of the record. */
    private String date;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
}
