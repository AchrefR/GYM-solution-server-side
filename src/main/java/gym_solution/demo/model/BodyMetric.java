package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A dated body-measurement snapshot for a member (weight / BMI / body-fat /
 * resting heart rate / calories burned), used to render progress charts over time.
 */
@Entity
@Table(name = "body_metric")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BodyMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bodyMetricId;

    /** ISO date string (yyyy-MM-dd) of the measurement. */
    private String date;

    private Double weight;

    private Double bmi;

    private Double bodyFat;

    private Double muscleMass;

    /** Resting heart rate in beats per minute. */
    private Integer restingHeartRate;

    /** Calories burned on the measurement day (kcal). */
    private Integer caloriesBurned;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
}
