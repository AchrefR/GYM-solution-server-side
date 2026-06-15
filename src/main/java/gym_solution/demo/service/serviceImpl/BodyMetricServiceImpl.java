package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.BodyMetricDTO;
import gym_solution.demo.dto.response.BodyMetricResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.mapper.BodyMetricMapper;
import gym_solution.demo.model.BodyMetric;
import gym_solution.demo.model.Member;
import gym_solution.demo.repository.BodyMetricRepository;
import gym_solution.demo.repository.MemberRepository;
import gym_solution.demo.service.BodyMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyMetricServiceImpl implements BodyMetricService {

    private final BodyMetricRepository bodyMetricRepository;

    private final MemberRepository memberRepository;

    private final BodyMetricMapper bodyMetricMapper;

    @Override
    @Transactional
    public BodyMetricResponseDTO addBodyMetric(BodyMetricDTO bodyMetricDTO) {
        Member member = this.memberRepository.findById(bodyMetricDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Member with id " + bodyMetricDTO.getMemberId() + " not found"));

        BodyMetric metric = this.bodyMetricMapper.toBodyMetric(bodyMetricDTO);
        metric.setMember(member);
        return this.bodyMetricMapper.toResponse(this.bodyMetricRepository.save(metric));
    }

    @Override
    @Transactional
    public BodyMetricResponseDTO updateBodyMetric(BodyMetricResponseDTO dto) {
        BodyMetric metric = this.bodyMetricRepository.findById(dto.getBodyMetricId())
                .orElseThrow(() -> new NotFoundException("BodyMetric with id " + dto.getBodyMetricId() + " not found"));
        metric.setDate(dto.getDate());
        metric.setWeight(dto.getWeight());
        metric.setBmi(dto.getBmi());
        metric.setBodyFat(dto.getBodyFat());
        metric.setMuscleMass(dto.getMuscleMass());
        metric.setRestingHeartRate(dto.getRestingHeartRate());
        metric.setCaloriesBurned(dto.getCaloriesBurned());
        return this.bodyMetricMapper.toResponse(this.bodyMetricRepository.save(metric));
    }

    @Override
    @Transactional
    public void deleteBodyMetricById(Long id) {
        if (!this.bodyMetricRepository.existsById(id)) {
            throw new NotFoundException("BodyMetric with id " + id + " not found");
        }
        this.bodyMetricRepository.deleteById(id);
    }

    @Override
    public BodyMetricResponseDTO findBodyMetricById(Long id) {
        return this.bodyMetricMapper.toResponse(this.bodyMetricRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BodyMetric with id " + id + " not found")));
    }

    @Override
    public List<BodyMetricResponseDTO> findByMemberId(Long memberId) {
        if (!this.memberRepository.existsById(memberId)) {
            throw new NotFoundException("Member with id " + memberId + " not found");
        }
        return this.bodyMetricMapper.toResponses(
                this.bodyMetricRepository.findByMember_MemberIdOrderByDateAsc(memberId));
    }
}
