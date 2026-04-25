package gym_solution.demo.controller;

import gym_solution.demo.dto.PaymentDTO;
import gym_solution.demo.dto.response.PaymentResponseDTO;
import gym_solution.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/")
    PaymentResponseDTO add(@RequestBody PaymentDTO paymentDTO) {
        return this.paymentService.addPayment(paymentDTO);
    }

    @PutMapping("/")
    PaymentResponseDTO update(@RequestBody PaymentResponseDTO paymentResponseDTO) {
        return this.paymentService.updatePayment(paymentResponseDTO);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        this.paymentService.deletePaymentById(id);
    }

    @GetMapping("/{id}")
    PaymentResponseDTO findById(@PathVariable Long id) {
        return this.paymentService.findPaymentById(id);
    }

    @GetMapping("/")
    List<PaymentResponseDTO> findPayments() {
        return this.paymentService.findAllPayments();
    }

}
