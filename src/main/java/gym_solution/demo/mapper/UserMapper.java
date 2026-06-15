package gym_solution.demo.mapper;

import gym_solution.demo.dto.UserDTO;
import gym_solution.demo.dto.response.UserResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleRepository roleRepository;

    /**
     * Maps a UserDTO to a User entity. The raw password is carried over as-is;
     * encoding is the service layer's responsibility (done exactly once there).
     */
    public User toUser(UserDTO userDTO) {

        Role role = this.roleRepository.findRoleByRoleName(userDTO.getRoleName())
                .orElseThrow(() -> new NotFoundException("Role '" + userDTO.getRoleName() + "' not found"));

        return User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(role)
                .build();
    }

    public UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .roleName(user.getRole().getRoleName())
                .build();
    }

    public List<UserResponseDTO> toResponses(List<User> userList) {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (User user : userList) {
            userResponseDTOS.add(toResponse(user));
        }
        return userResponseDTOS;
    }
}
