package gym_solution.demo.model;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

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

    private String description;

    private String date;

    private String startTime;

    private String endTime;

    private String duration;

    @ManyToMany(mappedBy = "workoutSessions")
    List<Member> members;
}
