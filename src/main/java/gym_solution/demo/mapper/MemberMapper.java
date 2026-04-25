package gym_solution.demo.mapper;

import gym_solution.demo.dto.MemberDTO;
import gym_solution.demo.dto.response.MemberResponseDTO;
import gym_solution.demo.model.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapper {

    public Member toMember(MemberDTO memberDTO) {
        return Member.builder().
                firstName(memberDTO.getFirstName()).
                lastName(memberDTO.getLastName()).
                email(memberDTO.getEmail()).
                phone(memberDTO.getPhone()).
                dateOfBirth(memberDTO.getDateOfBirth()).
                gender(memberDTO.getGender()).build();

    }

    public MemberResponseDTO toResponse(Member member) {
        return MemberResponseDTO.builder().
                memberId(member.getMemberId()).
                firstName(member.getFirstName()).
                lastName(member.getLastName()).
                email(member.getEmail()).
                phone(member.getPhone()).
                dateOfBirth(member.getDateOfBirth()).
                gender(member.getGender()).
                userId(member.getUser().getUserId())
                .build();
    }

    public List<MemberResponseDTO> toResponses(List<Member> members) {
        List<MemberResponseDTO> memberResponseDTOS = new ArrayList<>();
        members.forEach(member -> memberResponseDTOS.add(toResponse(member)));
        return memberResponseDTOS;
    }

}
