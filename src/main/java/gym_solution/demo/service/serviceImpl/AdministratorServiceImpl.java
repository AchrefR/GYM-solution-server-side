package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.AdministratorDTO;
import gym_solution.demo.dto.response.AdministratorResponseDTO;
import gym_solution.demo.mapper.AdministratorMapper;
import gym_solution.demo.model.Administrator;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.User;
import gym_solution.demo.repository.AdministratorRepository;
import gym_solution.demo.repository.RoleRepository;
import gym_solution.demo.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;

    private final AdministratorMapper administratorMapper;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    @Override
    public AdministratorResponseDTO addAdministrator(AdministratorDTO administratorDTO) {
        Role role = this.roleRepository.findRoleByRoleName("ADMIN").orElseThrow(() -> new RuntimeException("role is not found"));
        User administratorUser = User.builder().
                email(administratorDTO.getEmail()).
                password(encoder.encode(administratorDTO.getFirstName())).
                role(role).
                build();
        Administrator administrator = this.administratorMapper.toAdministrator(administratorDTO);
        administrator.setUser(administratorUser);
        administratorUser.setAdministrator(administrator);
        return this.administratorMapper.toResponse(this.administratorRepository.save(administrator));
    }

    @Override
    public AdministratorResponseDTO updateAdministrator(AdministratorResponseDTO administratorResponseDTO) {
        Administrator administrator = Administrator.builder().
                administratorId(administratorResponseDTO.getAdministratorId()).
                firstName(administratorResponseDTO.getFirstName()).
                lastName(administratorResponseDTO.getLastName()).
                email(administratorResponseDTO.getEmail()).
                phone(administratorResponseDTO.getPhone()).
                dateOfBirth(administratorResponseDTO.getDateOfBirth()).
                gender(administratorResponseDTO.getGender())
                .build();
        return this.administratorMapper.toResponse(this.administratorRepository.save(administrator));
    }

    @Override
    public void deleteAdministratorById(Long id) {
        this.administratorRepository.deleteById(id);
    }

    @Override
    public AdministratorResponseDTO findAdministratorById(Long id) {
        return this.administratorMapper.toResponse(this.administratorRepository.findById(id).orElseThrow(() -> new RuntimeException("administrator is not found")));
    }

    @Override
    public List<AdministratorResponseDTO> findAllAdministrators() {
        return this.administratorMapper.toResponses(this.administratorRepository.findAll());
    }
}
