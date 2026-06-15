package gym_solution.demo.controller;

import gym_solution.demo.dto.SubscriptionTypeDTO;
import gym_solution.demo.dto.response.SubscriptionTypeResponseDTO;
import gym_solution.demo.service.SubscriptionTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription-type")
@RequiredArgsConstructor
public class SubscriptionTypeController {

    private final SubscriptionTypeService subscriptionTypeService;

    @PostMapping("/")
    SubscriptionTypeResponseDTO add(@Valid @RequestBody SubscriptionTypeDTO dto) {
        return this.subscriptionTypeService.addSubscriptionType(dto);
    }

    @PutMapping("/{id}")
    SubscriptionTypeResponseDTO update(@PathVariable Long id, @Valid @RequestBody SubscriptionTypeDTO dto) {
        return this.subscriptionTypeService.updateSubscriptionType(id, dto);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        this.subscriptionTypeService.deleteSubscriptionTypeById(id);
    }

    @GetMapping("/{id}")
    SubscriptionTypeResponseDTO findById(@PathVariable Long id) {
        return this.subscriptionTypeService.findSubscriptionTypeById(id);
    }

    @GetMapping("/")
    List<SubscriptionTypeResponseDTO> findAll() {
        return this.subscriptionTypeService.findAllSubscriptionTypes();
    }
}
