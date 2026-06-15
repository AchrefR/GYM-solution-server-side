package gym_solution.demo.controller;

import gym_solution.demo.JWT.JwtUtil;
import gym_solution.demo.dto.LoginDTO;
import gym_solution.demo.dto.UserDTO;
import gym_solution.demo.dto.response.AuthResponseDTO;
import gym_solution.demo.dto.response.CurrentUserDTO;
import gym_solution.demo.dto.response.UserResponseDTO;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.UserRepository;
import gym_solution.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;
    private final UserService userService;

    public AuthController(UserRepository repo, JwtUtil jwtUtil, PasswordEncoder encoder, UserService userService) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserDTO userDTO) {
        return this.userService.addUser(userDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody LoginDTO loginDTO) {
        // Use the same generic message for "unknown email" and "wrong password"
        // so we don't leak which emails are registered.
        User dbUser = repo.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!encoder.matches(loginDTO.getPassword(), dbUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String role = dbUser.getRole() != null ? dbUser.getRole().getRoleName() : null;
        return AuthResponseDTO.builder()
                .token(jwtUtil.generateToken(dbUser.getEmail(), role))
                .build();
    }

    @GetMapping("/me")
    public CurrentUserDTO me(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication required");
        }
        return this.userService.getCurrentUser(authentication.getName());
    }
}
