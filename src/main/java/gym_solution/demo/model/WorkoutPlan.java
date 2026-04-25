package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workoutPlan")
@Builder
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workoutPlanId;

    private String name;

    private String description;

    private String goal;

    private String level;

    @ManyToMany(mappedBy = "workoutPlans")
    private List<Member> members;

}
