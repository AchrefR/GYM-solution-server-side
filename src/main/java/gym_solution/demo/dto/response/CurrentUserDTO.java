package gym_solution.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Unified view of the currently authenticated user, regardless of whether the
 * underlying profile is a Member, Trainer or Administrator.
 * profileId is the id of that profile (memberId / trainerId / administratorId), if any.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserDTO {

    private Long userId;
    private String email;
    private String roleName;

    private Long profileId;
    private String firstName;
    private String lastName;
    private String phone;
    private String dateOfBirth;
    private String gender;
}
