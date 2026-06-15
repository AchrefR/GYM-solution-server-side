package gym_solution.demo.controller;

import gym_solution.demo.dto.BodyMetricDTO;
import gym_solution.demo.dto.response.BodyMetricResponseDTO;
import gym_solution.demo.service.BodyMetricService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/body-metric")
@RequiredArgsConstructor
public class BodyMetricController {

    private final BodyMetricService bodyMetricService;

    @PostMapping("/")
    BodyMetricResponseDTO add(@Valid @RequestBody BodyMetricDTO bodyMetricDTO) {
        return this.bodyMetricService.addBodyMetric(bodyMetricDTO);
    }

    @PutMapping("/")
    BodyMetricResponseDTO update(@RequestBody BodyMetricResponseDTO bodyMetricResponseDTO) {
        return this.bodyMetricService.updateBodyMetric(bodyMetricResponseDTO);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        this.bodyMetricService.deleteBodyMetricById(id);
    }

    @GetMapping("/{id}")
    BodyMetricResponseDTO findById(@PathVariable Long id) {
        return this.bodyMetricService.findBodyMetricById(id);
    }

    @GetMapping("/member/{memberId}")
    List<BodyMetricResponseDTO> findByMember(@PathVariable Long memberId) {
        return this.bodyMetricService.findByMemberId(memberId);
    }
}
