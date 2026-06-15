package gym_solution.demo.service;

import gym_solution.demo.dto.TrainerDTO;
import gym_solution.demo.dto.response.MemberResponseDTO;
import gym_solution.demo.dto.response.TrainerResponseDTO;

import java.util.List;

public interface TrainerService {

    TrainerResponseDTO addTrainer(TrainerDTO trainerDTO);

    TrainerResponseDTO updateTrainer(TrainerResponseDTO trainerResponseDTO);

    void deleteTrainerById(Long id);

    TrainerResponseDTO findTrainerById(Long id);

    List<TrainerResponseDTO> findAllTrainers();

    /** Members assigned to the given trainer. */
    List<MemberResponseDTO> findMembersByTrainerId(Long trainerId);
}
