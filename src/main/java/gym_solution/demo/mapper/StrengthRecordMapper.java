package gym_solution.demo.mapper;

import gym_solution.demo.dto.StrengthRecordDTO;
import gym_solution.demo.dto.response.StrengthRecordResponseDTO;
import gym_solution.demo.model.StrengthRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StrengthRecordMapper {

    /** Maps the DTO to an entity. The member association is set by the service. */
    public StrengthRecord toStrengthRecord(StrengthRecordDTO dto) {
        return StrengthRecord.builder()
                .exercise(dto.getExercise())
                .weight(dto.getWeight())
                .reps(dto.getReps())
                .date(dto.getDate())
                .build();
    }

    public StrengthRecordResponseDTO toResponse(StrengthRecord record) {
        return StrengthRecordResponseDTO.builder()
                .strengthRecordId(record.getStrengthRecordId())
                .exercise(record.getExercise())
                .weight(record.getWeight())
                .reps(record.getReps())
                .date(record.getDate())
                .memberId(record.getMember() != null ? record.getMember().getMemberId() : null)
                .build();
    }

    public List<StrengthRecordResponseDTO> toResponses(List<StrengthRecord> records) {
        List<StrengthRecordResponseDTO> responses = new ArrayList<>();
        records.forEach(r -> responses.add(toResponse(r)));
        return responses;
    }
}
