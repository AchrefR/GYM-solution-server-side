package gym_solution.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class TrainerResponseDTO {

    private Long trainerId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String dateOfBirth;

    private String gender;

    private Long userId;
}
