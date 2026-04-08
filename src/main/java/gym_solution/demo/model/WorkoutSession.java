package gym_solution.demo.model;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "workoutSession")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workoutSessionId;

    private String title;

    private Long description;

    private Date date;

    private Time startTime;

    private Time endTime;

    private int duration;

}
