package gym_solution.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class BodyMetricResponseDTO {

    private Long bodyMetricId;
    private String date;
    private Double weight;
    private Double bmi;
    private Double bodyFat;
    private Double muscleMass;
    private Integer restingHeartRate;
    private Integer caloriesBurned;
    private Long memberId;
}
