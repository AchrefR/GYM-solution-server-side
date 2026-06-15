package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.StrengthRecordDTO;
import gym_solution.demo.dto.response.StrengthRecordResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.mapper.StrengthRecordMapper;
import gym_solution.demo.model.Member;
import gym_solution.demo.model.StrengthRecord;
import gym_solution.demo.repository.MemberRepository;
import gym_solution.demo.repository.StrengthRecordRepository;
import gym_solution.demo.service.StrengthRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StrengthRecordServiceImpl implements StrengthRecordService {

    private final StrengthRecordRepository strengthRecordRepository;

    private final MemberRepository memberRepository;

    private final StrengthRecordMapper strengthRecordMapper;

    @Override
    @Transactional
    public StrengthRecordResponseDTO addStrengthRecord(StrengthRecordDTO strengthRecordDTO) {
        Member member = this.memberRepository.findById(strengthRecordDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Member with id " + strengthRecordDTO.getMemberId() + " not found"));

        StrengthRecord record = this.strengthRecordMapper.toStrengthRecord(strengthRecordDTO);
        record.setMember(member);
        return this.strengthRecordMapper.toResponse(this.strengthRecordRepository.save(record));
    }

    @Override
    @Transactional
    public StrengthRecordResponseDTO updateStrengthRecord(StrengthRecordResponseDTO dto) {
        StrengthRecord record = this.strengthRecordRepository.findById(dto.getStrengthRecordId())
                .orElseThrow(() -> new NotFoundException("StrengthRecord with id " + dto.getStrengthRecordId() + " not found"));
        record.setExercise(dto.getExercise());
        record.setWeight(dto.getWeight());
        record.setReps(dto.getReps());
        record.setDate(dto.getDate());
        return this.strengthRecordMapper.toResponse(this.strengthRecordRepository.save(record));
    }

    @Override
    @Transactional
    public void deleteStrengthRecordById(Long id) {
        if (!this.strengthRecordRepository.existsById(id)) {
            throw new NotFoundException("StrengthRecord with id " + id + " not found");
        }
        this.strengthRecordRepository.deleteById(id);
    }

    @Override
    public StrengthRecordResponseDTO findStrengthRecordById(Long id) {
        return this.strengthRecordMapper.toResponse(this.strengthRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("StrengthRecord with id " + id + " not found")));
    }

    @Override
    public List<StrengthRecordResponseDTO> findByMemberId(Long memberId) {
        if (!this.memberRepository.existsById(memberId)) {
            throw new NotFoundException("Member with id " + memberId + " not found");
        }
        return this.strengthRecordMapper.toResponses(
                this.strengthRecordRepository.findByMember_MemberIdOrderByDateAsc(memberId));
    }
}
