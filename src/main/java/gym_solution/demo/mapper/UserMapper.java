package gym_solution.demo.mapper;

import gym_solution.demo.dto.UserDTO;
import gym_solution.demo.dto.response.UserResponseDTO;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public User toUser(UserDTO userDTO) {

        Role role = this.roleRepository.findRoleByRoleName(userDTO.getRoleName()).orElseThrow(() -> new RuntimeException("role is not found"));

        User user = User.builder().email(userDTO.getEmail()).password(userDTO.getPassword()).role(role).build();

        return user;
    }

    public UserResponseDTO toResponse(User user) {

        Role role = this.roleRepository.findById(user.getRole().getRoleId()).orElseThrow(() -> new RuntimeException("role is not found"));

        UserResponseDTO userResponseDTO = UserResponseDTO.builder().userId(user.getUserId()).email(user.getEmail()).roleName(role.getRoleName()).build();

        return userResponseDTO;
    }

    public List<UserResponseDTO> toResponses(List<User> userList)
    {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (User user : userList)
        {
            userResponseDTOS.add(toResponse(user));
        }
        return userResponseDTOS;
    }
}



