package gym_solution.demo.controller;

import gym_solution.demo.dto.TrainerDTO;
import gym_solution.demo.dto.response.TrainerResponseDTO;
import gym_solution.demo.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping("/")
    TrainerResponseDTO add(@RequestBody TrainerDTO trainerDTO) {
        return this.trainerService.addTrainer(trainerDTO);
    }

    @PutMapping("/")
    TrainerResponseDTO update(@RequestBody TrainerResponseDTO trainerResponseDTO) {
        return this.trainerService.updateTrainer(trainerResponseDTO);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        this.trainerService.deleteTrainerById(id);
    }

    @GetMapping("/{id}")
    TrainerResponseDTO findById(@PathVariable Long id) {
        return this.trainerService.findTrainerById(id);
    }

    @GetMapping("/")
    List<TrainerResponseDTO> findAll() {
        return this.trainerService.findAllTrainers();
    }
}
