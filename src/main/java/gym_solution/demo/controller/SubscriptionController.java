package gym_solution.demo.controller;

import gym_solution.demo.dto.SubscriptionDTO;
import gym_solution.demo.dto.response.SubscriptionResponseDTO;
import gym_solution.demo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/")
    SubscriptionResponseDTO add(@RequestBody SubscriptionDTO subscriptionDTO) {
        return this.subscriptionService.addSubscription(subscriptionDTO);
    }

    @PutMapping("/")
    SubscriptionResponseDTO update(@RequestBody SubscriptionResponseDTO subscriptionDTO) {
        return this.subscriptionService.updateSubscription(subscriptionDTO);
    }

    @DeleteMapping("/{id}")
    void deleteSubscriptionById(@PathVariable Long id) {
        this.subscriptionService.deleteSubscriptionById(id);
    }

    @GetMapping("/{id}")
    SubscriptionResponseDTO findSubscriptionById(@PathVariable Long id) {
        return this.subscriptionService.findSubscriptionById(id);
    }

    @GetMapping("/")
    List<SubscriptionResponseDTO> findAllSubscriptions() {
        return this.subscriptionService.findAllSubscriptions();
    }
}
