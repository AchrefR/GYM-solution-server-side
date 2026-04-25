package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.UserDTO;
import gym_solution.demo.dto.response.UserResponseDTO;
import gym_solution.demo.mapper.UserMapper;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.UserRepository;
import gym_solution.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public UserResponseDTO addUser(UserDTO userDTO) {

        User user = this.userMapper.toUser(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        return this.userMapper.toResponse(this.userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {

        this.userRepository.deleteById(id);

    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        return this.userMapper.toResponse(this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("user is not found")));

    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return this.userMapper.toResponses(this.userRepository.findAll());

    }
}
