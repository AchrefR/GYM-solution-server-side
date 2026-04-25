package gym_solution.demo.service;

import gym_solution.demo.dto.MemberDTO;
import gym_solution.demo.dto.response.MemberResponseDTO;

import java.util.List;

public interface MemberService {

    MemberResponseDTO addMember(MemberDTO memberDTO);

    MemberResponseDTO updateMember(MemberResponseDTO memberResponseDTO);

    void deleteMemberById(Long id);

    MemberResponseDTO findMemberById(Long id);

    List<MemberResponseDTO> findAllMembers();

}
