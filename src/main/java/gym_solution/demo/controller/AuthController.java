package gym_solution.demo.controller;

import gym_solution.demo.JWT.JwtUtil;
import gym_solution.demo.dto.LoginDTO;
import gym_solution.demo.dto.UserDTO;
import gym_solution.demo.dto.response.UserResponseDTO;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.RoleRepository;
import gym_solution.demo.repository.UserRepository;
import gym_solution.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final UserService userService;

    public AuthController(UserRepository repo, JwtUtil jwtUtil, PasswordEncoder encoder, RoleRepository roleRepository,UserService userService) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserDTO userDTO) {
        return this.userService.addUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        User dbUser = repo.findByEmail(loginDTO.getEmail())
                .orElseThrow();

        if (encoder.matches(loginDTO.getPassword(), dbUser.getPassword())) {
            return jwtUtil.generateToken(loginDTO.getEmail());
        }

        throw new RuntimeException("Invalid credentials");
    }
}