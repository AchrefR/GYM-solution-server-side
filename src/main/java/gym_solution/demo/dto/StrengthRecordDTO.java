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
public class StrengthRecordDTO {

    @NotBlank(message = "exercise is required")
    private String exercise;

    @NotNull(message = "weight is required")
    private Double weight;

    private Integer reps;

    @NotBlank(message = "date is required")
    private String date;

    @NotNull(message = "memberId is required")
    private Long memberId;
}
