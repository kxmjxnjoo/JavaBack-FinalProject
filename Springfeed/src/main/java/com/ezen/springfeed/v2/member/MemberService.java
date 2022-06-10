package com.ezen.springfeed.v2.member;

import com.ezen.springfeed.model.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // GET
    public Member getMemberByUserid(String userid) {
        Member member = memberRepository.findMemberByUserid(userid)
                .orElseThrow(() -> new IllegalStateException(
                        "일치하는 유저가 없어요"
                ));
        member.setPassword(null);
        return member;
    }

    public Member getMemberByNameAndEmail(Member member) {
        Member userid = memberRepository.findMemberByNameAndEmail(member.getName(), member.getEmail())
                .orElseThrow(() -> new IllegalStateException(
                        "일치하는 아이디가 없어요"
                ));

        return userid;
    }

    public List<Member> getAllMemberBySearchKey(String key) {
        return memberRepository.findAllByUseridContaining(key);
    }

    // POST
    public void addMember(Member member) {
        Optional<Member> memberByUserid = memberRepository.findMemberByUserid(member.getUserid());

        if (memberByUserid.isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디에요");
        }

        memberRepository.save(member);
    }

    // PUT
    @Transactional
    public void updateMember(Member updatedMember, String userid) {
        Member member = memberRepository.findMemberByUserid(userid)
                .orElseThrow(() -> new IllegalStateException(
                        updatedMember.getUserid() + "라는 아이디가 존재하지 않아요"
                ));

        if (updatedMember.getPassword() != null) {
            member.setPassword(updatedMember.getPassword());
        }

        if (updatedMember.getName() != null) {
            member.setName(updatedMember.getName());
        }

        if (updatedMember.getEmail() != null) {
            member.setEmail(updatedMember.getEmail());
        }

        if (updatedMember.getPhone() != null) {
            member.setPhone(updatedMember.getPhone());
        }

        if (updatedMember.getImg() != null) {
            member.setImg(updatedMember.getImg());
        }

        if (updatedMember.getIntroduce() != null) {
            member.setIntroduce(updatedMember.getIntroduce());
        }
    }

    // DELETE
    public void deleteMember(Member member) {
        Member memberByUserid = memberRepository.findMemberByUserid(member.getUserid())
                .orElseThrow(() -> new IllegalStateException(
                        "해당 아이디의 유저가 존재하지 않아요"
                ));

        if (memberByUserid.getPassword().equals(member.getPassword())) {
            memberRepository.deleteByUserid(member.getUserid());
            return;
        }

        new IllegalStateException("비밀번호를 다시 확인해 주세요");
    }

    public Member getMemberByUseridAndPassword(String userid, String password) {
        return memberRepository.findMemberByUseridAndPassword(userid, password)
                .orElseThrow(() -> new IllegalStateException(
                        "로그인할 수 없어요. 다시 시도해 주세요"
                ));
    }
}