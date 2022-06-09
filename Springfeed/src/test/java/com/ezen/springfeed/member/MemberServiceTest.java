package com.ezen.springfeed.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Captor
    private ArgumentCaptor<Member> memberArgumentCaptor;

    private MemberService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new MemberService(memberRepository);
    }

    // getMember
    @Test
    void isShouldGetMemberByUserid() {
        // Given
        String id = "testid";
        String pw = "testpw";
        Member member = new Member(id, pw);

        // When
        underTest.addMember(member);

        // Then
        Optional<Member> result = Optional.ofNullable(underTest.getMemberByUserid(id));
        assertThat(result).isEqualTo(member);
    }

    @Test
    void itShouldThrowWhenThereIsNoMember() {
        // Given
        String id = "testid";

        // When
        given(memberRepository);

        // Then
        assertThatThrownBy(() -> underTest.getMemberByUserid(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("일치");
    }

    // getMemberByNameAndEmail
    @Test
    void itShouldGetMemberByNameAndEmail() {

    }

    // getAllMemberBySearchKey
    @Test
    void isShouldGetAllMemberByUserid() {

    }

    @Test
    void itShouldGetAllMemberByName() {
    }

    // addMember
    @Test
    void itShouldSaveNewMember() {
        // Given
        String id = "testid";
        String pw = "testpw";
        Member member = new Member(id, pw);

        // When
        underTest.addMember(member);

        // Then
        then(memberRepository).should().save(memberArgumentCaptor.capture());
        Member memberArgumentCaptorValue = memberArgumentCaptor.getValue();
        assertThat(memberArgumentCaptorValue).isEqualTo(member);
    }

    @Test
    void itShouldNotSaveMemberWhenPwIsEmpty() {
        // Given
        String id = "testuserid";
        String pw = "";

        // When

        // Then

    }

    @Test
    void isShouldNotSaveNewMemberWhenThereIsDuplicate() {
        // Given
        String id = "testid";
        String pw = "testpw";
        Member member = new Member(id, pw);

        // When
        underTest.addMember(member);

        // Then
        then(memberRepository).should(never()).save(any());
    }

    // updateMember
    @Test
    void itShouldEditExistingMember() {

    }

    @Test
    void isShouldNotEditMemberWhenThereIsDuplicate() {

    }

    // deleteMember
    @Test
    void isShouldDeleteMember() {

    }
}