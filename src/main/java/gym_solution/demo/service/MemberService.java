package gym_solution.demo.service;

import gym_solution.demo.dto.MemberDTO;
import gym_solution.demo.dto.response.MemberResponseDTO;
import gym_solution.demo.dto.response.WorkoutPlanResponseDTO;
import gym_solution.demo.dto.response.WorkoutSessionResponseDTO;

import java.util.List;

public interface MemberService {

    MemberResponseDTO addMember(MemberDTO memberDTO);

    MemberResponseDTO updateMember(MemberResponseDTO memberResponseDTO);

    void deleteMemberById(Long id);

    MemberResponseDTO findMemberById(Long id);

    List<MemberResponseDTO> findAllMembers();

    // ---- Workout plan assignment ----
    List<WorkoutPlanResponseDTO> findWorkoutPlansByMemberId(Long memberId);

    void assignWorkoutPlan(Long memberId, Long workoutPlanId);

    void unassignWorkoutPlan(Long memberId, Long workoutPlanId);

    // ---- Workout session assignment ----
    List<WorkoutSessionResponseDTO> findWorkoutSessionsByMemberId(Long memberId);

    void assignWorkoutSession(Long memberId, Long workoutSessionId);

    void unassignWorkoutSession(Long memberId, Long workoutSessionId);
}
