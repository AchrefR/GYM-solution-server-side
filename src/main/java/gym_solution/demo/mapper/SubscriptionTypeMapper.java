package gym_solution.demo.mapper;

import gym_solution.demo.dto.SubscriptionTypeDTO;
import gym_solution.demo.dto.response.SubscriptionTypeResponseDTO;
import gym_solution.demo.model.SubscriptionType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubscriptionTypeMapper {

    public SubscriptionType toEntity(SubscriptionTypeDTO dto) {
        return SubscriptionType.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .durationMonths(dto.getDurationMonths())
                .features(joinFeatures(dto.getFeatures()))
                .build();
    }

    public SubscriptionTypeResponseDTO toResponse(SubscriptionType entity) {
        return SubscriptionTypeResponseDTO.builder()
                .subscriptionTypeId(entity.getSubscriptionTypeId())
                .name(entity.getName())
                .price(entity.getPrice())
                .durationMonths(entity.getDurationMonths())
                .features(splitFeatures(entity.getFeatures()))
                .build();
    }

    public List<SubscriptionTypeResponseDTO> toResponses(List<SubscriptionType> entities) {
        List<SubscriptionTypeResponseDTO> responses = new ArrayList<>();
        entities.forEach(e -> responses.add(toResponse(e)));
        return responses;
    }

    private String joinFeatures(List<String> features) {
        if (features == null || features.isEmpty()) return null;
        return String.join(",", features);
    }

    private List<String> splitFeatures(String features) {
        if (features == null || features.isBlank()) return new ArrayList<>();
        return Arrays.stream(features.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
