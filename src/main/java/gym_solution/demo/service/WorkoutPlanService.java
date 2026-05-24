package gym_solution.demo.service;

import gym_solution.demo.dto.WorkoutPlanDTO;
import gym_solution.demo.dto.response.WorkoutPlanResponseDTO;
import gym_solution.demo.model.WorkoutPlan;

import java.util.List;

public interface WorkoutPlanService {

    WorkoutPlanResponseDTO addWorkoutPlan(WorkoutPlanDTO workoutPlanDTO);

    WorkoutPlanResponseDTO updateWorkoutPlan(WorkoutPlanResponseDTO workoutPlanResponseDTO);

    void deleteWorkoutPlanById(Long id);

    WorkoutPlanResponseDTO findWorkoutPlanById(Long id);

    List<WorkoutPlanResponseDTO> findAllWorkoutPlans();

}
