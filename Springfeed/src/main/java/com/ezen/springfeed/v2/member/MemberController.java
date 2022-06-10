package com.ezen.springfeed.v2.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/member")
public class MemberController {

    private final MemberService ms;

    @Autowired
    public MemberController(MemberService ms) {
        this.ms = ms;
    }

    @GetMapping("/{userid}")
    public Member getMember(@PathVariable String userid) {
        return ms.getMemberByUserid(userid);
    }

    // Get userid by name and email
    @GetMapping("/userid")
    public Member getUseridByNameAndEmail(@RequestBody Member member) {
        return ms.getMemberByNameAndEmail(member);
    }

    @GetMapping
    public List<Member> getSearchResult(@RequestParam("key") String key) {
        return ms.getAllMemberBySearchKey(key);
    }

    // login
    @PostMapping("/login")
    public void login(@RequestBody Member member) {

    }

    @PostMapping
    public void addMember(@RequestBody Member member) {
        ms.addMember(member);
    }

    @PutMapping("{userid}")
    public void updateMember(@RequestBody Member member, @PathVariable("userid") String userid) {
        ms.updateMember(member, userid);
    }

    @DeleteMapping
    public void deleteMember(@RequestBody Member member) {
        ms.deleteMember(member);
    }
}
