package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.server.reactive.AbstractListenerWriteFlushProcessor;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @OneToOne(mappedBy = "user")
    private Member member;

    @OneToMany(mappedBy = "user")
    private List<Notification> notification;

    @OneToOne(mappedBy = "user")
    Administrator administrator;

    @OneToOne(mappedBy = "user")
    Trainer trainer;


}
