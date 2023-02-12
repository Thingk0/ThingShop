package com.thingk0.shopping.service;

import com.thingk0.shopping.dto.MemberForm;
import com.thingk0.shopping.entity.Member;
import com.thingk0.shopping.exception.UsernameAlreadyExistsException;
import com.thingk0.shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberForm memberForm, PasswordEncoder passwordEncoder) throws UsernameAlreadyExistsException {
        // 아이디 중복 가입 유효성 검사.
        validateDuplicateUsername(memberForm);
        Member member = Member.createMember(memberForm, passwordEncoder);
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateUsername(MemberForm memberForm) {
        Optional<Member> byUsername
                = memberRepository.findByUsername(memberForm.getUsername());
        if (byUsername.isPresent())
            throw new UsernameAlreadyExistsException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );

        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}


