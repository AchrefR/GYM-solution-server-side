package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscription")
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subscriptionId;

    private String type;

    private double price;

    private Date startDate;

    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "memberId")
    Member member;

    @OneToOne
    @JoinColumn(name = "paymentId")
    Payment payment;
}
