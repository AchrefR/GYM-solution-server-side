package gym_solution.demo.mapper;

import gym_solution.demo.dto.TrainerDTO;
import gym_solution.demo.dto.response.TrainerResponseDTO;
import gym_solution.demo.model.Trainer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainerMapper {

    public Trainer toTrainer(TrainerDTO trainerDTO) {
        return Trainer.builder().
                firstName(trainerDTO.getFirstName()).
                lastName(trainerDTO.getLastName()).
                email(trainerDTO.getEmail()).
                phone(trainerDTO.getPhone()).
                dateOfBirth(trainerDTO.getDateOfBirth()).
                gender(trainerDTO.getGender())
                .build();

    }

    public TrainerResponseDTO toResponse(Trainer trainer) {
        return TrainerResponseDTO.builder().
                trainerId(trainer.getTrainerId()).
                firstName(trainer.getFirstName()).
                lastName(trainer.getLastName()).
                email(trainer.getEmail()).
                phone(trainer.getPhone()).
                dateOfBirth(trainer.getDateOfBirth()).
                gender(trainer.getGender()).
                userId(trainer.getUser().getUserId())
                .build();
    }

    public List<TrainerResponseDTO> toResponses(List<Trainer> trainers) {
        List<TrainerResponseDTO> trainerResponseDTOS = new ArrayList<>();
        trainers.forEach(trainer -> trainerResponseDTOS.add(toResponse(trainer)));
        return trainerResponseDTOS;
    }
}
