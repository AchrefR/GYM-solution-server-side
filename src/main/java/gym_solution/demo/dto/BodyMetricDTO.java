package gym_solution.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class BodyMetricDTO {

    @NotBlank(message = "date is required")
    private String date;

    private Double weight;

    private Double bmi;

    private Double bodyFat;

    private Double muscleMass;

    private Integer restingHeartRate;

    private Integer caloriesBurned;

    @NotNull(message = "memberId is required")
    private Long memberId;
}
