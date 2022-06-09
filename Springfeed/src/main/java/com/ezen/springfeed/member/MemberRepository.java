package com.ezen.springfeed.member;

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
}