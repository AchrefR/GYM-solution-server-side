package gym_solution.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SubscriptionTypeDTO {

    @NotBlank(message = "name is required")
    private String name;

    @PositiveOrZero(message = "price must be zero or positive")
    private double price;

    private Integer durationMonths;

    /** Features as a list; persisted as a comma-separated string. */
    private List<String> features;
}
