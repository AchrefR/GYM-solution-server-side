package gym_solution.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class WorkoutSessionResponseDTO {

    private Long workoutSessionId;

    private String title;

    private String description;

    private String date;

    private String startTime;

    private String endTime;

    private String duration;
}
