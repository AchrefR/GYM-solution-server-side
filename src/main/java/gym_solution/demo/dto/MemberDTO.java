package gym_solution.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class MemberDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String dateOfBirth;

    private String gender;

}
