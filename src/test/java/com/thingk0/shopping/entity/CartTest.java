package com.thingk0.shopping.entity;

import com.thingk0.shopping.dto.MemberForm;
import com.thingk0.shopping.repository.CartRepository;
import com.thingk0.shopping.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager em;

    @Test
    public void findCardTest() {
        // 멤버 폼 생성
        MemberForm memberForm = new MemberForm();
        memberForm.setUsername("username");
        memberForm.setPassword("password");
        memberForm.setCity("seoul");
        memberForm.setStreet("seoul-street");
        memberForm.setPostcode("12345");

        Member member = Member.createMember(memberForm, passwordEncoder);
        Cart cart = new Cart(member);

        // when
        memberRepository.save(member);
        cartRepository.save(cart);

        em.flush();
        em.clear();

        Cart findCart = cartRepository.findById(cart.getId()).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 엔티티입니다.")
        );

        // then
        assertThat(findCart.getMember().getId()).isEqualTo(member.getId());

    }

}