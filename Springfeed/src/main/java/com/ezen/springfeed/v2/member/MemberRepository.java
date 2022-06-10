package com.ezen.springfeed.v2.member;

import com.ezen.springfeed.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByUserid(String userid);
    Optional<Member> findMemberByNameAndEmail(String name, String email);
    void deleteByUserid(String userid);
    List<Member> findAllByUseridContaining(String key);

    Optional<Member> findMemberByUseridAndPassword(String userid, String password);
}