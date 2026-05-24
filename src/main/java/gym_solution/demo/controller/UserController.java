package gym_solution.demo.controller;

import gym_solution.demo.dto.UserDTO;
import gym_solution.demo.dto.response.UserResponseDTO;
import gym_solution.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public UserResponseDTO addUser(@RequestBody UserDTO userDTO) {

        return this.userService.addUser(userDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {

        this.userService.deleteUserById(id);

    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {

        return this.userService.getUserById(id);

    }

    @GetMapping("/")
    public List<UserResponseDTO> getAllUsers() {

        return this.userService.getAllUsers();

    }

}
