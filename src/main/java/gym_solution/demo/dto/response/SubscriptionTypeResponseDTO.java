package gym_solution.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SubscriptionTypeResponseDTO {

    private Long subscriptionTypeId;
    private String name;
    private double price;
    private Integer durationMonths;
    private List<String> features;
}
