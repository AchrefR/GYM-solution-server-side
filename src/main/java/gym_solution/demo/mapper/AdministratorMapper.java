package gym_solution.demo.mapper;

import gym_solution.demo.dto.AdministratorDTO;
import gym_solution.demo.dto.response.AdministratorResponseDTO;
import gym_solution.demo.model.Administrator;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
@Builder
public class AdministratorMapper {

    public Administrator toAdministrator(AdministratorDTO administratorDTO) {
        return Administrator.builder().
                firstName(administratorDTO.getFirstName()).
                lastName(administratorDTO.getLastName()).
                email(administratorDTO.getEmail()).
                phone(administratorDTO.getPhone()).
                dateOfBirth(administratorDTO.getDateOfBirth()).
                gender(administratorDTO.getGender()).build();

    }

    public AdministratorResponseDTO toResponse(Administrator administrator) {
        return AdministratorResponseDTO.builder().
                administratorId(administrator.getAdministratorId()).
                firstName(administrator.getFirstName()).
                lastName(administrator.getLastName()).
                email(administrator.getEmail()).
                phone(administrator.getPhone()).
                dateOfBirth(administrator.getDateOfBirth()).
                gender(administrator.getGender())
                .build();
    }

    public List<AdministratorResponseDTO> toResponses(List<Administrator> administrators) {
        List<AdministratorResponseDTO> administratorResponseDTOS = new ArrayList<>();
        for (Administrator administrator : administrators) {
            administratorResponseDTOS.add(toResponse(administrator));
        }
        return administratorResponseDTOS;
    }
}
