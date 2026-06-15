package gym_solution.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class StrengthRecordResponseDTO {

    private Long strengthRecordId;
    private String exercise;
    private Double weight;
    private Integer reps;
    private String date;
    private Long memberId;
}
