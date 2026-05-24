package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscription")
@Builder
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subscriptionId;

    private double price;

    private String startDate;

    private String endDate;

    private String type;

    @ManyToOne
    @JoinColumn(name = "memberId")
    Member member;

    @OneToMany(mappedBy = "subscription")
    List<Payment> payments;
}
