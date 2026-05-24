package gym_solution.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class WorkoutPlanResponseDTO {

    private Long workoutPlanId;

    private String name;

    private String description;

    private String goal;

    private String level;

}
