package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
@Builder
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String dateOfBirth;

    private String gender;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE,CascadeType.MERGE})
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "member")
    List<Subscription> subscriptions;

    @ManyToMany
    @JoinTable(
            name = "workoutPlan_member",
            joinColumns = @JoinColumn(name = "workoutPlanId"),
            inverseJoinColumns = @JoinColumn(name = "memberId")
    )
    List<WorkoutPlan> workoutPlans;

    @ManyToMany
    @JoinTable(
            name = "workoutSession_member",
            joinColumns = @JoinColumn(name = "memberId"),
            inverseJoinColumns = @JoinColumn(name = "workoutSessionId")
    )
    List<WorkoutSession> workoutSessions;

    @ManyToOne
    @JoinColumn(name = "trainerId")
    Trainer trainer;

}
