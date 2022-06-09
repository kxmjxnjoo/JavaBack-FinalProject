package com.ezen.springfeed.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository underTest;

    @Test
    void itShouldFindMemberByUserid() {
        // given
        String id = "testuserid";
        String pw = "testpassword";
        Member member = new Member(id, pw);

        // when
        underTest.save(member);

        // then
        Optional<Member> expected = underTest.findMemberByUserid(id);
        assertThat(expected)
                .isPresent()
                        .hasValueSatisfying(m -> {
                            assertThat(m).isEqualTo(member);
                        });
    }

    @Test
    void itShouldFindMemberByNameAndEmail() {
        // Given
        String id = "testuserid";
        String pw = "testpassword";
        String name = "testname";
        String email = "testemail@gmail.com";
        Member member = new Member(id, pw, name, email);

        // When
        underTest.save(member);

        // Then
        Optional<Member> expected = underTest.findMemberByNameAndEmail(name, email);
        assertThat(expected)
                .isEqualTo(member);
    }

    @Test
    void itShouldDeleteByUserid() {
        // Given
        String id = "testuserid";
        String pw = "testpassword";
        Member member = new Member(id, pw);

        // When
        underTest.save(member);
        underTest.deleteByUserid(id);

        // Then
        Optional<Member> expected = underTest.findMemberByUserid(id);
        assertThat(expected)
                .isEmpty();
    }

    @Test
    void itShouldFindAllByUseridContaining() {
        // Given
        String keyWord = "word";
        String id1 = "test1" + keyWord;
        String id2 = "test2" + keyWord;
        String id3 = "test3" + keyWord;
        String pw = "testpassword";
        Member member1 = new Member(id1, pw);
        Member member2 = new Member(id2, pw);
        Member member3 = new Member(id3, pw);
        List<Member> test = new ArrayList<>();
        test.add(member1);
        test.add(member2);
        test.add(member3);

        // When
        underTest.save(member1);
        underTest.save(member2);
        underTest.save(member3);

        // Then
        List<Member> expected = underTest.findAllByUseridContaining(keyWord);
        assertThat(expected).isEqualTo(test);
    }
}