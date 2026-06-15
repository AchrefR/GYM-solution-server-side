package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.SubscriptionDTO;
import gym_solution.demo.dto.response.SubscriptionResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.mapper.SubscriptionMapper;
import gym_solution.demo.model.Member;
import gym_solution.demo.model.Subscription;
import gym_solution.demo.repository.MemberRepository;
import gym_solution.demo.repository.SubscriptionRepository;
import gym_solution.demo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionMapper subscriptionMapper;

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public SubscriptionResponseDTO addSubscription(SubscriptionDTO subscriptionDTO) {
        Member member = this.memberRepository.findById(subscriptionDTO.getMemberId()).orElseThrow(() -> new NotFoundException("Member with id " + subscriptionDTO.getMemberId() + " not found"));
        Subscription subscription = Subscription.builder().
                type(subscriptionDTO.getType()).
                price(subscriptionDTO.getPrice()).
                startDate(subscriptionDTO.getStartDate()).
                endDate(subscriptionDTO.getEndDate()).
                member(member)
                .build();
        return this.subscriptionMapper.toResponse(this.subscriptionRepository.save(subscription));
    }

    @Override
    @Transactional
    public SubscriptionResponseDTO updateSubscription(SubscriptionResponseDTO subscriptionDTO) {
        Subscription subscription = this.subscriptionRepository.findById(subscriptionDTO.getSubscriptionId()).orElseThrow(() -> new NotFoundException("Subscription with id " + subscriptionDTO.getSubscriptionId() + " not found"));
        subscription.setType(subscriptionDTO.getType());
        subscription.setPrice(subscriptionDTO.getPrice());
        subscription.setStartDate(subscriptionDTO.getStartDate());
        subscription.setEndDate(subscriptionDTO.getEndDate());
        return this.subscriptionMapper.toResponse(this.subscriptionRepository.save(subscription));
    }

    @Override
    @Transactional
    public void deleteSubscriptionById(Long id) {
        if (!this.subscriptionRepository.existsById(id)) {
            throw new NotFoundException("Subscription with id " + id + " not found");
        }
        this.subscriptionRepository.deleteById(id);
    }

    @Override
    public SubscriptionResponseDTO findSubscriptionById(Long id) {
        return this.subscriptionMapper.toResponse(this.subscriptionRepository.findById(id).orElseThrow(() -> new NotFoundException("Subscription with id " + id + " not found")));
    }

    @Override
    public List<SubscriptionResponseDTO> findAllSubscriptions() {
        return this.subscriptionMapper.toResponses(this.subscriptionRepository.findAll());
    }
}
