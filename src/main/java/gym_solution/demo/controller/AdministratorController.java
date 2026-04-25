package gym_solution.demo.controller;

import gym_solution.demo.dto.AdministratorDTO;
import gym_solution.demo.dto.response.AdministratorResponseDTO;
import gym_solution.demo.model.Administrator;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.User;
import gym_solution.demo.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
@RequiredArgsConstructor
public class AdministratorController {

    private final AdministratorService administratorService;

    @PostMapping("/")
    public AdministratorResponseDTO add(@RequestBody AdministratorDTO administratorDTO) {
        return this.administratorService.addAdministrator(administratorDTO);
    }

    @PutMapping("/")
    public AdministratorResponseDTO update(@RequestBody AdministratorResponseDTO administratorResponseDTO) {
        return this.administratorService.updateAdministrator(administratorResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.administratorService.deleteAdministratorById(id);
    }

    @GetMapping("/{id}")
    public AdministratorResponseDTO findById(@PathVariable Long id) {
        return this.administratorService.findAdministratorById(id);
    }

    @GetMapping("/")
    public List<AdministratorResponseDTO> findAll() {
        return this.administratorService.findAllAdministrators();
    }
}
