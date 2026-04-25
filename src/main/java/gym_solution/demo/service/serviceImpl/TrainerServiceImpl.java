package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.TrainerDTO;
import gym_solution.demo.dto.response.TrainerResponseDTO;
import gym_solution.demo.mapper.TrainerMapper;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.Trainer;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.RoleRepository;
import gym_solution.demo.repository.TrainerRepository;
import gym_solution.demo.repository.UserRepository;
import gym_solution.demo.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    private final TrainerMapper trainerMapper;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    @Override
    public TrainerResponseDTO addTrainer(TrainerDTO trainerDTO) {

        Role role = this.roleRepository.findRoleByRoleName("TRAINER").orElseThrow(() -> new RuntimeException("role is not found"));
        User user = User.builder().
                email(trainerDTO.getEmail()).
                password(encoder.encode(trainerDTO.getFirstName())).
                role(role)
                .build();
        Trainer trainer = this.trainerMapper.toTrainer(trainerDTO);
        trainer.setUser(user);
        return this.trainerMapper.toResponse(this.trainerRepository.save(trainer));
    }

    @Override
    public TrainerResponseDTO updateTrainer(TrainerResponseDTO trainerResponseDTO) {
        Trainer trainer = this.trainerRepository.findById(trainerResponseDTO.getTrainerId()).orElseThrow(() -> new RuntimeException("trainer is not found"));
        trainer.setFirstName(trainerResponseDTO.getFirstName());
        trainer.setLastName(trainerResponseDTO.getLastName());
        trainer.setEmail(trainerResponseDTO.getEmail());
        trainer.setPhone(trainerResponseDTO.getPhone());
        trainer.setDateOfBirth(trainerResponseDTO.getDateOfBirth());
        trainer.setGender(trainerResponseDTO.getGender());
        return this.trainerMapper.toResponse(this.trainerRepository.save(trainer));
    }

    @Override
    public void deleteTrainerById(Long id) {
        this.trainerRepository.deleteById(id);
    }

    @Override
    public TrainerResponseDTO findTrainerById(Long id) {
        return this.trainerMapper.toResponse(this.trainerRepository.findById(id).orElseThrow(() -> new RuntimeException("trainer is not found")));
    }

    @Override
    public List<TrainerResponseDTO> findAllTrainers() {
        return this.trainerMapper.toResponses(this.trainerRepository.findAll());
    }
}
