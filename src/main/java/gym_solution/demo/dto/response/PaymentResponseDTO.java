package gym_solution.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class PaymentResponseDTO {

    private Long paymentId;

    private double amount;

    private String date;

    private String method;

    private String status;

    private long subscriptionId;


}
