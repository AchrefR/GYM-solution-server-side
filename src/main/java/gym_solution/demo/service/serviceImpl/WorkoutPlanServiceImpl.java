package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.WorkoutPlanDTO;
import gym_solution.demo.dto.response.WorkoutPlanResponseDTO;
import gym_solution.demo.mapper.WorkoutPlanMapper;
import gym_solution.demo.model.WorkoutPlan;
import gym_solution.demo.repository.WorkoutPlanRepository;
import gym_solution.demo.service.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.function.array.ArrayContainsUnnestFunction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanMapper workoutPlanMapper;

    private final WorkoutPlanRepository workoutPlanRepository;

    @Override
    public WorkoutPlanResponseDTO addWorkoutPlan(WorkoutPlanDTO workoutPlanDTO) {
        return this.workoutPlanMapper.toResponse(this.workoutPlanRepository.save(this.workoutPlanMapper.toWorkoutPlan(workoutPlanDTO)));
    }

    @Override
    public WorkoutPlanResponseDTO updateWorkoutPlan(WorkoutPlanResponseDTO workoutPlanResponseDTO) {

        WorkoutPlan workoutPlan = this.workoutPlanRepository.findById(workoutPlanResponseDTO.getWorkoutPlanId()).orElseThrow(() -> new RuntimeException("workoutPlan is not found"));
        workoutPlan.setName(workoutPlanResponseDTO.getName());
        workoutPlan.setDescription(workoutPlanResponseDTO.getDescription());
        workoutPlan.setGoal(workoutPlanResponseDTO.getGoal());
        workoutPlan.setLevel(workoutPlanResponseDTO.getLevel());
        return this.workoutPlanMapper.toResponse(this.workoutPlanRepository.save(workoutPlan));
    }

    @Override
    public void deleteWorkoutPlanById(Long id) {
        this.workoutPlanRepository.deleteById(id);
    }

    @Override
    public WorkoutPlanResponseDTO findWorkoutPlanById(Long id) {
        return this.workoutPlanMapper.toResponse(this.workoutPlanRepository.findById(id).orElseThrow(() -> new RuntimeException("workout plan is not found")));
    }

    @Override
    public List<WorkoutPlanResponseDTO> findAllWorkoutPlans() {
        return this.workoutPlanMapper.toResponses(this.workoutPlanRepository.findAll());
    }
}
