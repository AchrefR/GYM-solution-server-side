package gym_solution.demo.mapper;

import gym_solution.demo.dto.BodyMetricDTO;
import gym_solution.demo.dto.response.BodyMetricResponseDTO;
import gym_solution.demo.model.BodyMetric;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BodyMetricMapper {

    /** Maps the DTO to an entity. The member association is set by the service. */
    public BodyMetric toBodyMetric(BodyMetricDTO dto) {
        return BodyMetric.builder()
                .date(dto.getDate())
                .weight(dto.getWeight())
                .bmi(dto.getBmi())
                .bodyFat(dto.getBodyFat())
                .muscleMass(dto.getMuscleMass())
                .restingHeartRate(dto.getRestingHeartRate())
                .caloriesBurned(dto.getCaloriesBurned())
                .build();
    }

    public BodyMetricResponseDTO toResponse(BodyMetric metric) {
        return BodyMetricResponseDTO.builder()
                .bodyMetricId(metric.getBodyMetricId())
                .date(metric.getDate())
                .weight(metric.getWeight())
                .bmi(metric.getBmi())
                .bodyFat(metric.getBodyFat())
                .muscleMass(metric.getMuscleMass())
                .restingHeartRate(metric.getRestingHeartRate())
                .caloriesBurned(metric.getCaloriesBurned())
                .memberId(metric.getMember() != null ? metric.getMember().getMemberId() : null)
                .build();
    }

    public List<BodyMetricResponseDTO> toResponses(List<BodyMetric> metrics) {
        List<BodyMetricResponseDTO> responses = new ArrayList<>();
        metrics.forEach(m -> responses.add(toResponse(m)));
        return responses;
    }
}
