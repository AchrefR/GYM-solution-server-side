package gym_solution.demo.controller;

import gym_solution.demo.dto.MemberDTO;
import gym_solution.demo.dto.response.MemberResponseDTO;
import gym_solution.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/")
    MemberResponseDTO add(@RequestBody MemberDTO memberDTO)
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


}
