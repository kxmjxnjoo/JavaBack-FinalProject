package com.ezen.springfeed.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository mr;

    public MemberService(MemberRepository mr) {
        this.mr = mr;
    }

    // GET
    public Member getMember(String userid) {
        Optional<Member> memberByUserid = mr.findMemberByUserid(userid);

        if(memberByUserid.isPresent()) {
            return memberByUserid.get();
        }

        return null;
    }

    public Member getMemberByNameAndEmail(Member member) {
        Member userid = mr.findMemberByNameAndEmail(member.getName(), member.getEmail())
                .orElseThrow(() -> new IllegalStateException(
                        "일치하는 아이디가 없어요"
                ));

        return userid;
    }

    // POST
    public void addMember(Member member) {
        Optional<Member> memberByUserid = mr.findMemberByUserid(member.getUserid());

        if(memberByUserid.isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디에요");
        }

        mr.save(member);
    }

    // PUT
    @Transactional
    public void updateMember(Member updatedMember) {
        Member member = mr.findMemberByUserid(updatedMember.getUserid())
                .orElseThrow(() -> new IllegalStateException(
                        updatedMember.getUserid() + "라는 아이디가 존재하지 않아요"
                ));

        if(updatedMember.getPassword() != null) {
            member.setPassword(updatedMember.getPassword());
        }

        if(updatedMember.getName() != null) {
            member.setName(updatedMember.getName());
        }

        if(updatedMember.getEmail() != null) {
            member.setEmail(updatedMember.getEmail());
        }

        if(updatedMember.getPhone() != null) {
            member.setPhone(updatedMember.getPhone());
        }

        if(updatedMember.getImg() != null) {
            member.setImg(updatedMember.getImg());
        }

        if(updatedMember.getIntroduce() != null) {
            member.setIntroduce(updatedMember.getIntroduce());
        }
    }

    // DELETE
    public void deleteMember(Member member) {
        Member memberByUserid = mr.findMemberByUserid(member.getUserid())
                .orElseThrow(() -> new IllegalStateException(
                        "해당 아이디의 유저가 존재하지 않아요"
                ));

        if(memberByUserid.getPassword().equals(member.getPassword())) {
            mr.deleteByUserid(member.getUserid());
            return;
        }

        new IllegalStateException("비밀번호를 다시 확인해 주세요");
    }

}