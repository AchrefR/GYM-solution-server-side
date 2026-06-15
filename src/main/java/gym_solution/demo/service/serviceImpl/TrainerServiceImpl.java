package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.TrainerDTO;
import gym_solution.demo.dto.response.MemberResponseDTO;
import gym_solution.demo.dto.response.TrainerResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.mapper.MemberMapper;
import gym_solution.demo.mapper.TrainerMapper;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.Trainer;
import gym_solution.demo.model.User;
import gym_solution.demo.pattern.factory.MemberRole;
import gym_solution.demo.repository.MemberRepository;
import gym_solution.demo.repository.RoleRepository;
import gym_solution.demo.repository.TrainerRepository;
import gym_solution.demo.repository.UserRepository;
import gym_solution.demo.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    private final TrainerMapper trainerMapper;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public TrainerResponseDTO addTrainer(TrainerDTO trainerDTO) {

        if (this.userRepository.findByEmail(trainerDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A user with email '" + trainerDTO.getEmail() + "' already exists");
        }

        Role role = this.roleRepository.findRoleByRoleName(MemberRole.TRAINER.getRoleName())
                .orElseThrow(() -> new NotFoundException("Role 'TRAINER' not found"));
        User user = User.builder()
                .email(trainerDTO.getEmail())
                // Encode the real password supplied by the client (not the first name).
                .password(encoder.encode(trainerDTO.getPassword()))
                .role(role)
                .build();
        Trainer trainer = this.trainerMapper.toTrainer(trainerDTO);
        trainer.setUser(user);
        return this.trainerMapper.toResponse(this.trainerRepository.save(trainer));
    }

    @Override
    @Transactional
    public TrainerResponseDTO updateTrainer(TrainerResponseDTO trainerResponseDTO) {
        Trainer trainer = this.trainerRepository.findById(trainerResponseDTO.getTrainerId())
                .orElseThrow(() -> new NotFoundException("Trainer with id " + trainerResponseDTO.getTrainerId() + " not found"));
        trainer.setFirstName(trainerResponseDTO.getFirstName());
        trainer.setLastName(trainerResponseDTO.getLastName());
        trainer.setEmail(trainerResponseDTO.getEmail());
        trainer.setPhone(trainerResponseDTO.getPhone());
        trainer.setDateOfBirth(trainerResponseDTO.getDateOfBirth());
        trainer.setGender(trainerResponseDTO.getGender());
        return this.trainerMapper.toResponse(this.trainerRepository.save(trainer));
    }

    @Override
    @Transactional
    public void deleteTrainerById(Long id) {
        if (!this.trainerRepository.existsById(id)) {
            throw new NotFoundException("Trainer with id " + id + " not found");
        }
        this.trainerRepository.deleteById(id);
    }

    @Override
    public TrainerResponseDTO findTrainerById(Long id) {
        return this.trainerMapper.toResponse(this.trainerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trainer with id " + id + " not found")));
    }

    @Override
    public List<TrainerResponseDTO> findAllTrainers() {
        return this.trainerMapper.toResponses(this.trainerRepository.findAll());
    }

    @Override
    public List<MemberResponseDTO> findMembersByTrainerId(Long trainerId) {
        if (!this.trainerRepository.existsById(trainerId)) {
            throw new NotFoundException("Trainer with id " + trainerId + " not found");
        }
        return this.memberMapper.toResponses(this.memberRepository.findByTrainer_TrainerId(trainerId));
    }
}
