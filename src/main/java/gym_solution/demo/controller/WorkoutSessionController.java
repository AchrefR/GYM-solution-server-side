package gym_solution.demo.controller;

import gym_solution.demo.dto.WorkoutSessionDTO;
import gym_solution.demo.dto.response.WorkoutSessionResponseDTO;
import gym_solution.demo.service.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workoutSession")
@RequiredArgsConstructor
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @PostMapping("/")
    public WorkoutSessionResponseDTO add(@RequestBody WorkoutSessionDTO workoutSessionDTO) {
        return this.workoutSessionService.addWorkoutSession(workoutSessionDTO);
    }

    @PutMapping("/")
    public WorkoutSessionResponseDTO update(@RequestBody WorkoutSessionResponseDTO workoutSessionResponseDTO) {
        return this.workoutSessionService.updateWorkoutSession(workoutSessionResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.workoutSessionService.deleteWorkoutSession(id);
    }

    @GetMapping("/{id}")
    public WorkoutSessionResponseDTO findById(@PathVariable Long id) {
        return this.workoutSessionService.findWorkoutSessionById(id);
    }

    @GetMapping("/")
    public List<WorkoutSessionResponseDTO> findAll() {
        return this.workoutSessionService.findAllWorkoutSessions();
    }

}
