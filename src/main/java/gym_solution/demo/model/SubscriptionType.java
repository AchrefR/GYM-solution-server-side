package gym_solution.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Catalog of subscription plans (e.g. Basic / Pro / Elite) that members can subscribe to.
 * This is the plan template; an actual member purchase is a {@link Subscription}.
 */
@Entity
@Table(name = "subscription_type", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subscriptionTypeId;

    private String name;

    private double price;

    /** Billing period in months (e.g. 1 = monthly). */
    private Integer durationMonths;

    /** Comma-separated feature list, kept simple to match the existing string-based model. */
    @Column(length = 1000)
    private String features;
}
