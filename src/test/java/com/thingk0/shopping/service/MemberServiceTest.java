package com.thingk0.shopping.service;

import com.thingk0.shopping.dto.MemberForm;
import com.thingk0.shopping.entity.Member;
import com.thingk0.shopping.exception.UsernameAlreadyExistsException;
import com.thingk0.shopping.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() throws Exception {
        // given
        MemberForm memberForm = new MemberForm();
        memberForm.setUsername("thingk0");
        memberForm.setPassword("1234");

        // when
        Long savedId = memberService.save(memberForm);

        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(savedId).orElseThrow(
                () -> new IllegalStateException("해당 ID의 유저를 찾을 수 없음.")
        );

        // then
        Assertions.assertThat(findMember.getUsername()).isEqualTo(memberForm.getUsername());
    }

    @Test
    @DisplayName("아이디 중복 검사 테스트")
    public void validateDuplicateUsernameTest() throws Exception {
        // given
        MemberForm member1 = new MemberForm();
        member1.setUsername("thingk0");
        member1.setPassword("1234");

        MemberForm member2 = new MemberForm();
        member2.setUsername("thingk0");
        member2.setPassword("1234");

        // when
        memberService.save(member1);

        em.flush();
        em.clear();

        // then
        UsernameAlreadyExistsException exception = org.junit.jupiter.api.Assertions.assertThrows(
                UsernameAlreadyExistsException.class,
                () -> memberService.save(member2)
        );

        Assertions.assertThat(exception.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }
}