package gym_solution.demo.service;


import gym_solution.demo.dto.PaymentDTO;
import gym_solution.demo.dto.response.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {

    PaymentResponseDTO addPayment(PaymentDTO paymentDTO);

    PaymentResponseDTO updatePayment(PaymentResponseDTO paymentResponseDTO);

    void deletePaymentById(Long id);

    PaymentResponseDTO findPaymentById(Long id);

    List<PaymentResponseDTO> findAllPayments();


}
