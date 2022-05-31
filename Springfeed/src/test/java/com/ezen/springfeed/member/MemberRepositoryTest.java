package com.ezen.springfeed.member;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class MemberRepositoryTest {

    private MemberRepository underTest;

    public MemberRepositoryTest(MemberRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    void itShouldFindMemberByUserid() {
        // given
        Member member = new Member(
                123456789L,
                "Example Userid",
                "ExamplePassword",
                "Example Name",
                "Example@Email.com",
                "01045398503",
                "Exampole useyn",
                "Example Introduce",
                "Example Image",
                Date.valueOf(LocalDate.now())
        );
        underTest.save(member);

        // when
        Optional<Member> expected = underTest.findMemberByUserid(member.getUserid());

        // then
        assertThat(expected).isPresent();
    }

    @Test
    void findMemberByNameAndEmail() {
    }

    @Test
    void deleteByUserid() {
    }
}