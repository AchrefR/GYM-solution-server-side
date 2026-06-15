package gym_solution.demo.controller;

import gym_solution.demo.dto.StrengthRecordDTO;
import gym_solution.demo.dto.response.StrengthRecordResponseDTO;
import gym_solution.demo.service.StrengthRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/strength-record")
@RequiredArgsConstructor
public class StrengthRecordController {

    private final StrengthRecordService strengthRecordService;

    @PostMapping("/")
    StrengthRecordResponseDTO add(@Valid @RequestBody StrengthRecordDTO strengthRecordDTO) {
        return this.strengthRecordService.addStrengthRecord(strengthRecordDTO);
    }

    @PutMapping("/")
    StrengthRecordResponseDTO update(@RequestBody StrengthRecordResponseDTO strengthRecordResponseDTO) {
        return this.strengthRecordService.updateStrengthRecord(strengthRecordResponseDTO);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        this.strengthRecordService.deleteStrengthRecordById(id);
    }

    @GetMapping("/{id}")
    StrengthRecordResponseDTO findById(@PathVariable Long id) {
        return this.strengthRecordService.findStrengthRecordById(id);
    }

    @GetMapping("/member/{memberId}")
    List<StrengthRecordResponseDTO> findByMember(@PathVariable Long memberId) {
        return this.strengthRecordService.findByMemberId(memberId);
    }
}
