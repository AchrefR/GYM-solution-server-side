package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trainer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trainerId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String dateOfBirth;

    private String gender;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE,CascadeType.MERGE})
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "trainer")
    List<Member> members;


}
