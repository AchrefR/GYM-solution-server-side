package gym_solution.demo.mapper;

import gym_solution.demo.dto.SubscriptionDTO;
import gym_solution.demo.dto.response.SubscriptionResponseDTO;
import gym_solution.demo.model.Subscription;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscriptionMapper {

    public Subscription toSubscription(SubscriptionDTO subscriptionDTO) {
        return Subscription.builder().
                type(subscriptionDTO.getType()).
                price(subscriptionDTO.getPrice()).
                startDate(subscriptionDTO.getStartDate()).
                endDate(subscriptionDTO.getEndDate())
                .build();
    }

    public SubscriptionResponseDTO toResponse(Subscription subscription) {
        return SubscriptionResponseDTO.builder().
                subscriptionId(subscription.getSubscriptionId()).
                type(subscription.getType()).
                price(subscription.getPrice()).
                startDate(subscription.getStartDate()).
                endDate(subscription.getEndDate()).
                memberId(subscription.getMember().getMemberId())
                .build();
    }

    public List<SubscriptionResponseDTO> toResponses(List<Subscription> subscriptions) {
        List<SubscriptionResponseDTO> subscriptionResponseDTOS = new ArrayList<>();
        subscriptions.forEach(subscription -> subscriptionResponseDTOS.add(toResponse(subscription)));
        return subscriptionResponseDTOS;
    }
}
