package gym_solution.demo.controller;

import gym_solution.demo.dto.WorkoutPlanDTO;
import gym_solution.demo.dto.response.WorkoutPlanResponseDTO;
import gym_solution.demo.service.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workoutPlan")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    @PostMapping("/")
    public WorkoutPlanResponseDTO add(@RequestBody WorkoutPlanDTO workoutPlanDTO) {
        return this.workoutPlanService.addWorkoutPlan(workoutPlanDTO);
    }

    @PutMapping("/")
    public WorkoutPlanResponseDTO update(@RequestBody WorkoutPlanResponseDTO workoutPlanResponseDTO) {
        return this.workoutPlanService.updateWorkoutPlan(workoutPlanResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.workoutPlanService.deleteWorkoutPlanById(id);
    }

    @GetMapping("/{id}")
    public WorkoutPlanResponseDTO findById(@PathVariable Long id) {
        return this.workoutPlanService.findWorkoutPlanById(id);
    }

    @GetMapping("/")
    public List<WorkoutPlanResponseDTO> findAll() {
        return this.workoutPlanService.findAllWorkoutPlans();
    }
}
