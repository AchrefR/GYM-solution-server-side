package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.MemberDTO;
import gym_solution.demo.dto.response.MemberResponseDTO;
import gym_solution.demo.dto.response.WorkoutPlanResponseDTO;
import gym_solution.demo.dto.response.WorkoutSessionResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.mapper.MemberMapper;
import gym_solution.demo.mapper.WorkoutPlanMapper;
import gym_solution.demo.mapper.WorkoutSessionMapper;
import gym_solution.demo.model.Member;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.User;
import gym_solution.demo.model.WorkoutPlan;
import gym_solution.demo.model.WorkoutSession;
import gym_solution.demo.pattern.factory.MemberRole;
import gym_solution.demo.repository.MemberRepository;
import gym_solution.demo.repository.RoleRepository;
import gym_solution.demo.repository.UserRepository;
import gym_solution.demo.repository.WorkoutPlanRepository;
import gym_solution.demo.repository.WorkoutSessionRepository;
import gym_solution.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    private final MemberRepository memberRepository;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final WorkoutPlanRepository workoutPlanRepository;

    private final WorkoutSessionRepository workoutSessionRepository;

    private final WorkoutPlanMapper workoutPlanMapper;

    private final WorkoutSessionMapper workoutSessionMapper;

    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public MemberResponseDTO addMember(MemberDTO memberDTO) {

        if (this.userRepository.findByEmail(memberDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A user with email '" + memberDTO.getEmail() + "' already exists");
        }

        Role role = this.roleRepository.findRoleByRoleName(MemberRole.MEMBER.getRoleName())
                .orElseThrow(() -> new NotFoundException("Role 'MEMBER' not found"));

        User user = User.builder()
                .email(memberDTO.getEmail())
                // Encode the real password supplied by the client (not the first name).
                .password(encoder.encode(memberDTO.getPassword()))
                .role(role)
                .build();

        Member member = this.memberMapper.toMember(memberDTO);
        member.setUser(user);
        return this.memberMapper.toResponse(this.memberRepository.save(member));
    }

    @Override
    @Transactional
    public MemberResponseDTO updateMember(MemberResponseDTO memberResponseDTO) {
        Member member = this.memberRepository.findById(memberResponseDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Member with id " + memberResponseDTO.getMemberId() + " not found"));
        member.setFirstName(memberResponseDTO.getFirstName());
        member.setLastName(memberResponseDTO.getLastName());
        member.setEmail(memberResponseDTO.getEmail());
        member.setPhone(memberResponseDTO.getPhone());
        member.setDateOfBirth(memberResponseDTO.getDateOfBirth());
        member.setGender(memberResponseDTO.getGender());
        return this.memberMapper.toResponse(this.memberRepository.save(member));
    }

    @Override
    @Transactional
    public void deleteMemberById(Long id) {
        if (!this.memberRepository.existsById(id)) {
            throw new NotFoundException("Member with id " + id + " not found");
        }
        this.memberRepository.deleteById(id);
    }

    @Override
    public MemberResponseDTO findMemberById(Long id) {
        return this.memberMapper.toResponse(
                this.memberRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Member with id " + id + " not found")));
    }

    @Override
    public List<MemberResponseDTO> findAllMembers() {
        return this.memberMapper.toResponses(this.memberRepository.findAll());
    }

    // ---- Workout plan assignment ----

    @Override
    public List<WorkoutPlanResponseDTO> findWorkoutPlansByMemberId(Long memberId) {
        Member member = getMemberOrThrow(memberId);
        List<WorkoutPlan> plans = member.getWorkoutPlans() != null ? member.getWorkoutPlans() : new ArrayList<>();
        return this.workoutPlanMapper.toResponses(plans);
    }

    @Override
    @Transactional
    public void assignWorkoutPlan(Long memberId, Long workoutPlanId) {
        Member member = getMemberOrThrow(memberId);
        WorkoutPlan plan = this.workoutPlanRepository.findById(workoutPlanId)
                .orElseThrow(() -> new NotFoundException("WorkoutPlan with id " + workoutPlanId + " not found"));

        if (member.getWorkoutPlans() == null) {
            member.setWorkoutPlans(new ArrayList<>());
        }
        boolean alreadyAssigned = member.getWorkoutPlans().stream()
                .anyMatch(p -> p.getWorkoutPlanId().equals(workoutPlanId));
        if (!alreadyAssigned) {
            member.getWorkoutPlans().add(plan);
            this.memberRepository.save(member);
        }
    }

    @Override
    @Transactional
    public void unassignWorkoutPlan(Long memberId, Long workoutPlanId) {
        Member member = getMemberOrThrow(memberId);
        if (member.getWorkoutPlans() != null) {
            member.getWorkoutPlans().removeIf(p -> p.getWorkoutPlanId().equals(workoutPlanId));
            this.memberRepository.save(member);
        }
    }

    // ---- Workout session assignment ----

    @Override
    public List<WorkoutSessionResponseDTO> findWorkoutSessionsByMemberId(Long memberId) {
        Member member = getMemberOrThrow(memberId);
        List<WorkoutSession> sessions = member.getWorkoutSessions() != null ? member.getWorkoutSessions() : new ArrayList<>();
        return this.workoutSessionMapper.toResponses(sessions);
    }

    @Override
    @Transactional
    public void assignWorkoutSession(Long memberId, Long workoutSessionId) {
        Member member = getMemberOrThrow(memberId);
        WorkoutSession session = this.workoutSessionRepository.findById(workoutSessionId)
                .orElseThrow(() -> new NotFoundException("WorkoutSession with id " + workoutSessionId + " not found"));

        if (member.getWorkoutSessions() == null) {
            member.setWorkoutSessions(new ArrayList<>());
        }
        boolean alreadyAssigned = member.getWorkoutSessions().stream()
                .anyMatch(s -> s.getWorkoutSessionId().equals(workoutSessionId));
        if (!alreadyAssigned) {
            member.getWorkoutSessions().add(session);
            this.memberRepository.save(member);
        }
    }

    @Override
    @Transactional
    public void unassignWorkoutSession(Long memberId, Long workoutSessionId) {
        Member member = getMemberOrThrow(memberId);
        if (member.getWorkoutSessions() != null) {
            member.getWorkoutSessions().removeIf(s -> s.getWorkoutSessionId().equals(workoutSessionId));
            this.memberRepository.save(member);
        }
    }

    private Member getMemberOrThrow(Long memberId) {
        return this.memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("Member with id " + memberId + " not found"));
    }
}
