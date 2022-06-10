package com.ezen.springfeed.v2.member;

import com.ezen.springfeed.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v2/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{userid}")
    public Member getMember(@PathVariable String userid) {
        return memberService.getMemberByUserid(userid);
    }

    // Get userid by name and email
    @GetMapping("/userid")
    public Member getUseridByNameAndEmail(@RequestBody Member member) {
        return memberService.getMemberByNameAndEmail(member);
    }

    @GetMapping
    public List<Member> getSearchResult(@RequestParam("key") String key) {
        return memberService.getAllMemberBySearchKey(key);
    }

    // login
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Member member, HttpServletRequest request) {
        Map<String, String> message = new HashMap<>();

        Member loginUser = memberService.getMemberByUseridAndPassword(member.getUserid(), member.getPassword());

        if(loginUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            message.put("message", "success");
            return message;
        }

        message.put("message", "failure");
        return message;
    }

    @PostMapping
    public void addMember(@RequestBody Member member) {
        memberService.addMember(member);
    }

    @PutMapping("{userid}")
    public void updateMember(@RequestBody Member member, @PathVariable("userid") String userid) {
        memberService.updateMember(member, userid);
    }

    @DeleteMapping
    public void deleteMember(@RequestBody Member member) {
        memberService.deleteMember(member);
    }
}
