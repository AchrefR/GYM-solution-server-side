package gym_solution.demo.service;

import gym_solution.demo.dto.AdministratorDTO;
import gym_solution.demo.dto.response.AdministratorResponseDTO;

import java.util.List;

public interface AdministratorService {

    AdministratorResponseDTO addAdministrator(AdministratorDTO administratorDTO);

    AdministratorResponseDTO updateAdministrator(AdministratorResponseDTO administratorResponseDTO);

    void deleteAdministratorById(Long id);

    AdministratorResponseDTO findAdministratorById(Long id);

    List<AdministratorResponseDTO> findAllAdministrators();


}
