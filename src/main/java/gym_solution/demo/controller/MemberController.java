package gym_solution.demo.controller;

import gym_solution.demo.dto.MemberDTO;
import gym_solution.demo.dto.response.MemberResponseDTO;
import gym_solution.demo.dto.response.WorkoutPlanResponseDTO;
import gym_solution.demo.dto.response.WorkoutSessionResponseDTO;
import gym_solution.demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/")
    MemberResponseDTO add(@Valid @RequestBody MemberDTO memberDTO)
    {
        return this.memberService.addMember(memberDTO);
    }

    @PutMapping("/")
    MemberResponseDTO update(@RequestBody MemberResponseDTO memberResponseDTO)
    {
        return this.memberService.updateMember(memberResponseDTO);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id)
    {
        this.memberService.deleteMemberById(id);
    }

    @GetMapping("/{id}")
    MemberResponseDTO findById(@PathVariable Long id)
    {
        return this.memberService.findMemberById(id);
    }

    @GetMapping("/")
    List<MemberResponseDTO> findAll()
    {
        return this.memberService.findAllMembers();
    }

    // ---- Workout plan assignment ----

    @GetMapping("/{id}/workout-plans")
    List<WorkoutPlanResponseDTO> findWorkoutPlans(@PathVariable Long id) {
        return this.memberService.findWorkoutPlansByMemberId(id);
    }

    @PostMapping("/{id}/workout-plans/{planId}")
    void assignWorkoutPlan(@PathVariable Long id, @PathVariable Long planId) {
        this.memberService.assignWorkoutPlan(id, planId);
    }

    @DeleteMapping("/{id}/workout-plans/{planId}")
    void unassignWorkoutPlan(@PathVariable Long id, @PathVariable Long planId) {
        this.memberService.unassignWorkoutPlan(id, planId);
    }

    // ---- Workout session assignment ----

    @GetMapping("/{id}/workout-sessions")
    List<WorkoutSessionResponseDTO> findWorkoutSessions(@PathVariable Long id) {
        return this.memberService.findWorkoutSessionsByMemberId(id);
    }

    @PostMapping("/{id}/workout-sessions/{sessionId}")
    void assignWorkoutSession(@PathVariable Long id, @PathVariable Long sessionId) {
        this.memberService.assignWorkoutSession(id, sessionId);
    }

    @DeleteMapping("/{id}/workout-sessions/{sessionId}")
    void unassignWorkoutSession(@PathVariable Long id, @PathVariable Long sessionId) {
        this.memberService.unassignWorkoutSession(id, sessionId);
    }
}
