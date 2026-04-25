package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    private double amount;

    private Date date;

    private String method;

    private String status;

    @ManyToOne
    @JoinColumn(name = "subscriptionID")
    private Subscription subscription;
}
