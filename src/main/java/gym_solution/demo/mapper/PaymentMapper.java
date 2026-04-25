package gym_solution.demo.mapper;

import gym_solution.demo.dto.PaymentDTO;
import gym_solution.demo.dto.response.PaymentResponseDTO;
import gym_solution.demo.model.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentMapper {

    public Payment toPayment(PaymentDTO paymentDTO) {
        return Payment.builder().
                amount(paymentDTO.getAmount()).
                date(paymentDTO.getDate()).
                method(paymentDTO.getMethod()).
                status(paymentDTO.getStatus())
                .build();
    }

    public PaymentResponseDTO toResponse(Payment payment) {
        return PaymentResponseDTO.builder().
                paymentId(payment.getPaymentId()).
                amount(payment.getAmount()).
                date(payment.getDate()).
                method(payment.getMethod()).
                status(payment.getStatus()).
                subscriptionId(payment.getSubscription().getSubscriptionId())
                .build();
    }

    public List<PaymentResponseDTO> toResponses(List<Payment> payments) {
        List<PaymentResponseDTO> paymentResponseDTOS = new ArrayList<>();
        payments.forEach(payment -> paymentResponseDTOS.add(toResponse(payment)));
        return paymentResponseDTOS;
    }

}
