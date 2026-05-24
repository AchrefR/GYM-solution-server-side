package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.PaymentDTO;
import gym_solution.demo.dto.response.PaymentResponseDTO;
import gym_solution.demo.mapper.PaymentMapper;
import gym_solution.demo.model.Payment;
import gym_solution.demo.model.Subscription;
import gym_solution.demo.repository.PaymentRepository;
import gym_solution.demo.repository.SubscriptionRepository;
import gym_solution.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDTO addPayment(PaymentDTO paymentDTO) {
        Subscription subscription = this.subscriptionRepository.findById(paymentDTO.
                        getSubscriptionId()).
                orElseThrow(() -> new RuntimeException("subscription is not found"));

        Payment payment = this.paymentMapper.toPayment(paymentDTO);
        payment.setSubscription(subscription);

        return this.paymentMapper.toResponse(this.paymentRepository.save(payment));
    }

    @Override
    public PaymentResponseDTO updatePayment(PaymentResponseDTO paymentResponseDTO) {

        Payment payment = this.paymentRepository.findById(paymentResponseDTO.getPaymentId()).orElseThrow(() -> new RuntimeException("payment is not found"));
        payment.setAmount(paymentResponseDTO.getAmount());
        payment.setDate(paymentResponseDTO.getDate());
        payment.setMethod(paymentResponseDTO.getMethod());
        payment.setStatus(paymentResponseDTO.getStatus());
        return this.paymentMapper.toResponse(this.paymentRepository.save(payment));
    }

    @Override
    public void deletePaymentById(Long id) {
        this.paymentRepository.deleteById(id);
    }

    @Override
    public PaymentResponseDTO findPaymentById(Long id) {
        return this.paymentMapper.toResponse(this.paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("payment is not found")));
    }

    @Override
    public List<PaymentResponseDTO> findAllPayments() {
        return this.paymentMapper.toResponses(this.paymentRepository.findAll());
    }
}
