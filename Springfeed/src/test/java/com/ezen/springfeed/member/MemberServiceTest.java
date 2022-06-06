package com.ezen.springfeed.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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

    }

    @Test
    void itShouldThrowWhenThereIsNoMember() {

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
