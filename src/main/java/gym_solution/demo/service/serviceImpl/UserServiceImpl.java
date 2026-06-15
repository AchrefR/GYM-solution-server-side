package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.UserDTO;
import gym_solution.demo.dto.response.CurrentUserDTO;
import gym_solution.demo.dto.response.UserResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.mapper.UserMapper;
import gym_solution.demo.model.Administrator;
import gym_solution.demo.model.Member;
import gym_solution.demo.model.Trainer;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.AdministratorRepository;
import gym_solution.demo.repository.MemberRepository;
import gym_solution.demo.repository.TrainerRepository;
import gym_solution.demo.repository.UserRepository;
import gym_solution.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final MemberRepository memberRepository;

    private final TrainerRepository trainerRepository;

    private final AdministratorRepository administratorRepository;

    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public UserResponseDTO addUser(UserDTO userDTO) {

        if (this.userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A user with email '" + userDTO.getEmail() + "' already exists");
        }

        User user = this.userMapper.toUser(userDTO);
        // Encode here — the single place passwords are hashed.
        user.setPassword(encoder.encode(user.getPassword()));
        return this.userMapper.toResponse(this.userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if (!this.userRepository.existsById(id)) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        this.userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return this.userMapper.toResponse(
                this.userRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("User with id " + id + " not found")));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return this.userMapper.toResponses(this.userRepository.findAll());
    }

    @Override
    public CurrentUserDTO getCurrentUser(String email) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email '" + email + "' not found"));

        String roleName = user.getRole() != null ? user.getRole().getRoleName() : null;

        CurrentUserDTO.CurrentUserDTOBuilder dto = CurrentUserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .roleName(roleName);

        // Enrich with the role-specific profile when one exists.
        if ("MEMBER".equals(roleName)) {
            this.memberRepository.findByUser_Email(email).ifPresent(m -> applyMember(dto, m));
        } else if ("TRAINER".equals(roleName)) {
            this.trainerRepository.findByUser_Email(email).ifPresent(t -> applyTrainer(dto, t));
        } else if ("ADMIN".equals(roleName)) {
            this.administratorRepository.findByUser_Email(email).ifPresent(a -> applyAdmin(dto, a));
        }

        return dto.build();
    }

    private void applyMember(CurrentUserDTO.CurrentUserDTOBuilder dto, Member m) {
        dto.profileId(m.getMemberId())
                .firstName(m.getFirstName())
                .lastName(m.getLastName())
                .phone(m.getPhone())
                .dateOfBirth(m.getDateOfBirth())
                .gender(m.getGender());
    }

    private void applyTrainer(CurrentUserDTO.CurrentUserDTOBuilder dto, Trainer t) {
        dto.profileId(t.getTrainerId())
                .firstName(t.getFirstName())
                .lastName(t.getLastName())
                .phone(t.getPhone())
                .dateOfBirth(t.getDateOfBirth())
                .gender(t.getGender());
    }

    private void applyAdmin(CurrentUserDTO.CurrentUserDTOBuilder dto, Administrator a) {
        dto.profileId(a.getAdministratorId())
                .firstName(a.getFirstName())
                .lastName(a.getLastName())
                .phone(a.getPhone())
                .dateOfBirth(a.getDateOfBirth())
                .gender(a.getGender());
    }
}
