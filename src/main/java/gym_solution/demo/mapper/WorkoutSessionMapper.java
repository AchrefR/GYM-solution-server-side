package gym_solution.demo.mapper;

import gym_solution.demo.dto.WorkoutSessionDTO;
import gym_solution.demo.dto.response.WorkoutSessionResponseDTO;
import gym_solution.demo.model.WorkoutSession;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkoutSessionMapper {

    public WorkoutSession toWorkoutSession(WorkoutSessionDTO workoutSessionDTO) {
        return WorkoutSession.builder().
                title(workoutSessionDTO.getTitle()).
                description(workoutSessionDTO.getDescription()).
                date(workoutSessionDTO.getDate()).
                startTime(workoutSessionDTO.getStartTime()).
                endTime(workoutSessionDTO.getEndTime()).
                duration(workoutSessionDTO.getDuration())
                .build();
    }

    public WorkoutSessionResponseDTO toResponse(WorkoutSession workoutSession) {
        return WorkoutSessionResponseDTO.builder().
                workoutSessionId(workoutSession.getWorkoutSessionId()).
                title(workoutSession.getTitle()).
                description(workoutSession.getDescription()).
                date(workoutSession.getDate()).
                startTime(workoutSession.getStartTime()).
                endTime(workoutSession.getEndTime()).
                duration(workoutSession.getDuration())
                .build();
    }

    public List<WorkoutSessionResponseDTO> toResponses(List<WorkoutSession> workoutSessions) {
        List<WorkoutSessionResponseDTO> workoutSessionResponseDTOS = new ArrayList<>();
        workoutSessions.forEach(workoutSession -> workoutSessionResponseDTOS.add(toResponse(workoutSession)));
        return workoutSessionResponseDTOS;
    }

}
