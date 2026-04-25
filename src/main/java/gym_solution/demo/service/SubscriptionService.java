package gym_solution.demo.service;

import gym_solution.demo.dto.SubscriptionDTO;
import gym_solution.demo.dto.response.SubscriptionResponseDTO;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponseDTO addSubscription(SubscriptionDTO subscriptionDTO);

    SubscriptionResponseDTO updateSubscription(SubscriptionResponseDTO subscriptionDTO);

    void deleteSubscriptionById(Long id);

    SubscriptionResponseDTO findSubscriptionById(Long id);

    List<SubscriptionResponseDTO> findAllSubscriptions();
}
