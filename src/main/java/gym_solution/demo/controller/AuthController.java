package gym_solution.demo.controller;

import gym_solution.demo.JWT.JwtUtil;
import gym_solution.demo.dto.UserDTO;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.RoleRepository;
import gym_solution.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository repo, JwtUtil jwtUtil, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        Role role = roleRepository.findRoleByRoleName("ADMIN");
        user.setPassword(encoder.encode(user.getPassword()));
        User user1 = User.builder().email(user.getEmail()).password(user.getPassword()).role(role).build();
        repo.save(user1);

        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = repo.findByEmail(user.getEmail())
                .orElseThrow();

        if (encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwtUtil.generateToken(user.getEmail());
        }

        throw new RuntimeException("Invalid credentials");
    }
}