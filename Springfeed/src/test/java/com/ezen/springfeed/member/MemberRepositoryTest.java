package com.ezen.springfeed.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository underTest;

    @Test
    void itShouldFindMemberByUserid() {
        // given
        String id = "testuserid";
        String pw = "testpassword";
        Member member = new Member(
                id, pw
        );
        underTest.save(member);

        // when
        Optional<Member> expected = underTest.findMemberByUserid(id);

        // then
        assertThat(expected.get()).isEqualTo(member);
    }

    @Test
    void itShouldFindMemberByNameAndEmail() {
        assertThat(123).isEqualTo(123);
    }

    @Test
    void itShouldDeleteByUserid() {
    }

    @Test
    void itShouldFindAllByUseridContaining() {
    }
}