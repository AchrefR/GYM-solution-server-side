package gym_solution.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class WorkoutPlanDTO {

    private String name;

    private String description;

    private String goal;

    private String level;
}
