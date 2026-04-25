package gym_solution.demo.service;

import gym_solution.demo.dto.WorkoutSessionDTO;
import gym_solution.demo.dto.response.WorkoutSessionResponseDTO;

import java.util.List;

public interface WorkoutSessionService {

    WorkoutSessionResponseDTO addWorkoutSession(WorkoutSessionDTO workoutSessionDTO);

    WorkoutSessionResponseDTO updateWorkoutSession(WorkoutSessionResponseDTO workoutSessionResponseDTO);

    void deleteWorkoutSession(Long id);

    WorkoutSessionResponseDTO findWorkoutSessionById(Long id);

    List<WorkoutSessionResponseDTO> findAllWorkoutSessions();


}
