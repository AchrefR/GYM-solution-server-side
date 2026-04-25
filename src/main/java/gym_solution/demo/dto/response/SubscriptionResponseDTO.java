package gym_solution.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SubscriptionResponseDTO {

    private Long subscriptionId;

    private String type;

    private double price;

    private String startDate;

    private String endDate;

    private Long memberId;
}
