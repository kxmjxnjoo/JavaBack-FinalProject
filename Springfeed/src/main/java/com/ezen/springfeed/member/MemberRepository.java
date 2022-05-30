package com.ezen.springfeed.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUserid(String userid);
    Optional<Member> findMemberByNameAndEmail(String name, String email);

    void deleteByUserid(String userid);
}