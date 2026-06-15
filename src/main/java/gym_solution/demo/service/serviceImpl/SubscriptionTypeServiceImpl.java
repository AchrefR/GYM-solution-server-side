package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.SubscriptionTypeDTO;
import gym_solution.demo.dto.response.SubscriptionTypeResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.mapper.SubscriptionTypeMapper;
import gym_solution.demo.model.SubscriptionType;
import gym_solution.demo.repository.SubscriptionTypeRepository;
import gym_solution.demo.service.SubscriptionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    private final SubscriptionTypeMapper subscriptionTypeMapper;

    @Override
    @Transactional
    public SubscriptionTypeResponseDTO addSubscriptionType(SubscriptionTypeDTO dto) {
        if (this.subscriptionTypeRepository.findByName(dto.getName()).isPresent()) {
            throw new IllegalArgumentException("A subscription plan named '" + dto.getName() + "' already exists");
        }
        SubscriptionType entity = this.subscriptionTypeMapper.toEntity(dto);
        return this.subscriptionTypeMapper.toResponse(this.subscriptionTypeRepository.save(entity));
    }

    @Override
    @Transactional
    public SubscriptionTypeResponseDTO updateSubscriptionType(Long id, SubscriptionTypeDTO dto) {
        SubscriptionType entity = this.subscriptionTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubscriptionType with id " + id + " not found"));
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDurationMonths(dto.getDurationMonths());
        entity.setFeatures(dto.getFeatures() == null ? null : String.join(",", dto.getFeatures()));
        return this.subscriptionTypeMapper.toResponse(this.subscriptionTypeRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteSubscriptionTypeById(Long id) {
        if (!this.subscriptionTypeRepository.existsById(id)) {
            throw new NotFoundException("SubscriptionType with id " + id + " not found");
        }
        this.subscriptionTypeRepository.deleteById(id);
    }

    @Override
    public SubscriptionTypeResponseDTO findSubscriptionTypeById(Long id) {
        return this.subscriptionTypeMapper.toResponse(this.subscriptionTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubscriptionType with id " + id + " not found")));
    }

    @Override
    public List<SubscriptionTypeResponseDTO> findAllSubscriptionTypes() {
        return this.subscriptionTypeMapper.toResponses(this.subscriptionTypeRepository.findAll());
    }
}
