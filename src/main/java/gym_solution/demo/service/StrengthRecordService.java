package gym_solution.demo.service;

import gym_solution.demo.dto.StrengthRecordDTO;
import gym_solution.demo.dto.response.StrengthRecordResponseDTO;

import java.util.List;

public interface StrengthRecordService {

    StrengthRecordResponseDTO addStrengthRecord(StrengthRecordDTO strengthRecordDTO);

    StrengthRecordResponseDTO updateStrengthRecord(StrengthRecordResponseDTO strengthRecordResponseDTO);

    void deleteStrengthRecordById(Long id);

    StrengthRecordResponseDTO findStrengthRecordById(Long id);

    /** All lift records for a member, oldest first. */
    List<StrengthRecordResponseDTO> findByMemberId(Long memberId);
}
