package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.MemberDTO;
import gym_solution.demo.dto.response.MemberResponseDTO;
import gym_solution.demo.mapper.MemberMapper;
import gym_solution.demo.model.Member;
import gym_solution.demo.model.Role;
import gym_solution.demo.model.User;

import gym_solution.demo.repository.MemberRepository;
import gym_solution.demo.repository.RoleRepository;
import gym_solution.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    private final MemberRepository memberRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    @Override
    public MemberResponseDTO addMember(MemberDTO memberDTO) {
        Role role = this.roleRepository.findRoleByRoleName("MEMBER").orElseThrow(() -> new RuntimeException("role is not found"));
        User user = User.builder().
                email(memberDTO.getEmail()).
                password(encoder.encode(memberDTO.getFirstName())).
                role(role)
                .build();
        Member member = this.memberMapper.toMember(memberDTO);
        member.setUser(user);
        return this.memberMapper.toResponse(this.memberRepository.save(member));
    }

    @Override
    public MemberResponseDTO updateMember(MemberResponseDTO memberResponseDTO) {
//        User user = this.userRepository.findById(memberResponseDTO.getUserId()).orElseThrow(() -> new RuntimeException("user is not found"));
        Member member = this.memberRepository.findById(memberResponseDTO.getMemberId()).orElseThrow(() -> new RuntimeException("member n'existe pas "));
        member.setFirstName(memberResponseDTO.getFirstName());
        member.setLastName(memberResponseDTO.getLastName());
        member.setEmail(memberResponseDTO.getEmail());
        member.setPhone(memberResponseDTO.getPhone());
        member.setDateOfBirth(memberResponseDTO.getDateOfBirth());
        member.setGender(memberResponseDTO.getGender());
        return this.memberMapper.toResponse(this.memberRepository.save(member));
    }


    @Override
    public void deleteMemberById(Long id) {
        this.memberRepository.deleteById(id);
    }

    @Override
    public MemberResponseDTO findMemberById(Long id) {
        return this.memberMapper.toResponse(this.memberRepository.findById(id).orElseThrow(() -> new RuntimeException("member is not found")));
    }

    @Override
    public List<MemberResponseDTO> findAllMembers() {
        return this.memberMapper.toResponses(this.memberRepository.findAll());
    }


}
