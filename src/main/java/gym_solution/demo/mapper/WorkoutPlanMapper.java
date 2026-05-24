package gym_solution.demo.mapper;

import gym_solution.demo.dto.WorkoutPlanDTO;
import gym_solution.demo.dto.response.WorkoutPlanResponseDTO;
import gym_solution.demo.model.WorkoutPlan;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
@Builder
public class WorkoutPlanMapper {

    public WorkoutPlan toWorkoutPlan(WorkoutPlanDTO workoutPlanDTO) {
        return WorkoutPlan.builder().
                name(workoutPlanDTO.getName()).
                description(workoutPlanDTO.getDescription()).
                goal(workoutPlanDTO.getGoal()).
                level(workoutPlanDTO.getLevel())
                .build();
    }

    public WorkoutPlanResponseDTO toResponse(WorkoutPlan workoutPlan) {
        return WorkoutPlanResponseDTO.builder().
                workoutPlanId(workoutPlan.getWorkoutPlanId()).
                name(workoutPlan.getName()).
                description(workoutPlan.getDescription()).
                goal(workoutPlan.getGoal()).
                level(workoutPlan.getLevel()).
                build();
    }

    public List<WorkoutPlanResponseDTO> toResponses(List<WorkoutPlan> workoutPlans) {
        List<WorkoutPlanResponseDTO> workoutPlanResponseDTOS = new ArrayList<>();
        workoutPlans.forEach(workoutPlan -> workoutPlanResponseDTOS.add(toResponse(workoutPlan)));
        return workoutPlanResponseDTOS;
    }

}
