package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.WorkoutSessionDTO;
import gym_solution.demo.dto.response.WorkoutSessionResponseDTO;
import gym_solution.demo.mapper.WorkoutSessionMapper;
import gym_solution.demo.model.WorkoutSession;
import gym_solution.demo.repository.WorkoutSessionRepository;
import gym_solution.demo.service.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutSessionServiceImpl implements WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;

    private final WorkoutSessionMapper workoutSessionMapper;

    @Override
    public WorkoutSessionResponseDTO addWorkoutSession(WorkoutSessionDTO workoutSessionDTO) {
        return this.workoutSessionMapper.toResponse(this.workoutSessionRepository.save(this.workoutSessionMapper.toWorkoutSession(workoutSessionDTO)));
    }

    @Override
    public WorkoutSessionResponseDTO updateWorkoutSession(WorkoutSessionResponseDTO workoutSessionResponseDTO) {
        WorkoutSession workoutSession = this.workoutSessionRepository.findById(workoutSessionResponseDTO.getWorkoutSessionId()).orElseThrow(() -> new RuntimeException("workout session is not found"));
        workoutSession.setTitle(workoutSessionResponseDTO.getTitle());
        workoutSession.setDescription(workoutSessionResponseDTO.getDescription());
        workoutSession.setDate(workoutSessionResponseDTO.getDate());
        workoutSession.setStartTime(workoutSessionResponseDTO.getStartTime());
        workoutSession.setEndTime(workoutSessionResponseDTO.getEndTime());
        workoutSession.setDuration(workoutSessionResponseDTO.getDuration());
        return this.workoutSessionMapper.toResponse(this.workoutSessionRepository.save(workoutSession));
    }

    @Override
    public void deleteWorkoutSession(Long id) {
        this.workoutSessionRepository.deleteById(id);
    }

    @Override
    public WorkoutSessionResponseDTO findWorkoutSessionById(Long id) {
        return this.workoutSessionMapper.toResponse(this.workoutSessionRepository.findById(id).orElseThrow(() -> new RuntimeException("workout session is not found")));
    }

    @Override
    public List<WorkoutSessionResponseDTO> findAllWorkoutSessions() {
        return this.workoutSessionMapper.toResponses(this.workoutSessionRepository.findAll());
    }
}
