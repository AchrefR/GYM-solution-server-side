package gym_solution.demo.service;

import gym_solution.demo.dto.BodyMetricDTO;
import gym_solution.demo.dto.response.BodyMetricResponseDTO;

import java.util.List;

public interface BodyMetricService {

    BodyMetricResponseDTO addBodyMetric(BodyMetricDTO bodyMetricDTO);

    BodyMetricResponseDTO updateBodyMetric(BodyMetricResponseDTO bodyMetricResponseDTO);

    void deleteBodyMetricById(Long id);

    BodyMetricResponseDTO findBodyMetricById(Long id);

    /** All measurements for a member, oldest first. */
    List<BodyMetricResponseDTO> findByMemberId(Long memberId);
}
