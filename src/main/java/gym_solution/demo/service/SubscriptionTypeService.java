package gym_solution.demo.service;

import gym_solution.demo.dto.SubscriptionTypeDTO;
import gym_solution.demo.dto.response.SubscriptionTypeResponseDTO;

import java.util.List;

public interface SubscriptionTypeService {

    SubscriptionTypeResponseDTO addSubscriptionType(SubscriptionTypeDTO dto);

    SubscriptionTypeResponseDTO updateSubscriptionType(Long id, SubscriptionTypeDTO dto);

    void deleteSubscriptionTypeById(Long id);

    SubscriptionTypeResponseDTO findSubscriptionTypeById(Long id);

    List<SubscriptionTypeResponseDTO> findAllSubscriptionTypes();
}
