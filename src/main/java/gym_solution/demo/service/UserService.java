package gym_solution.demo.service;

import gym_solution.demo.dto.UserDTO;
import gym_solution.demo.dto.response.CurrentUserDTO;
import gym_solution.demo.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

     UserResponseDTO addUser(UserDTO userDTO);

     void deleteUserById(Long id);

     UserResponseDTO getUserById(Long id);

     List<UserResponseDTO> getAllUsers();

     /** Resolves the full profile (member/trainer/admin) for the authenticated email. */
     CurrentUserDTO getCurrentUser(String email);

}
