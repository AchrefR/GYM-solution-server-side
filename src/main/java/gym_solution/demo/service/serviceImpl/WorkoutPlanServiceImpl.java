package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.WorkoutPlanDTO;
import gym_solution.demo.dto.response.WorkoutPlanResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.mapper.WorkoutPlanMapper;
import gym_solution.demo.model.WorkoutPlan;
import gym_solution.demo.repository.WorkoutPlanRepository;
import gym_solution.demo.service.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanMapper workoutPlanMapper;

    private final WorkoutPlanRepository workoutPlanRepository;

    @Override
    @Transactional
    public WorkoutPlanResponseDTO addWorkoutPlan(WorkoutPlanDTO workoutPlanDTO) {
        return this.workoutPlanMapper.toResponse(this.workoutPlanRepository.save(this.workoutPlanMapper.toWorkoutPlan(workoutPlanDTO)));
    }

    @Override
    @Transactional
    public WorkoutPlanResponseDTO updateWorkoutPlan(WorkoutPlanResponseDTO workoutPlanResponseDTO) {

        WorkoutPlan workoutPlan = this.workoutPlanRepository.findById(workoutPlanResponseDTO.getWorkoutPlanId()).orElseThrow(() -> new NotFoundException("WorkoutPlan with id " + workoutPlanResponseDTO.getWorkoutPlanId() + " not found"));
        workoutPlan.setName(workoutPlanResponseDTO.getName());
        workoutPlan.setDescription(workoutPlanResponseDTO.getDescription());
        workoutPlan.setGoal(workoutPlanResponseDTO.getGoal());
        workoutPlan.setLevel(workoutPlanResponseDTO.getLevel());
        return this.workoutPlanMapper.toResponse(this.workoutPlanRepository.save(workoutPlan));
    }

    @Override
    @Transactional
    public void deleteWorkoutPlanById(Long id) {
        if (!this.workoutPlanRepository.existsById(id)) {
            throw new NotFoundException("WorkoutPlan with id " + id + " not found");
        }
        this.workoutPlanRepository.deleteById(id);
    }

    @Override
    public WorkoutPlanResponseDTO findWorkoutPlanById(Long id) {
        return this.workoutPlanMapper.toResponse(this.workoutPlanRepository.findById(id).orElseThrow(() -> new NotFoundException("WorkoutPlan with id " + id + " not found")));
    }

    @Override
    public List<WorkoutPlanResponseDTO> findAllWorkoutPlans() {
        return this.workoutPlanMapper.toResponses(this.workoutPlanRepository.findAll());
    }
}
