package com.thingk0.shopping.entity;

import com.thingk0.shopping.dto.MemberForm;
import com.thingk0.shopping.entity.embedded.Address;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "member")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberForm memberForm, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(memberForm.getUsername())
                .password(passwordEncoder.encode(memberForm.getPassword()))
                .address(new Address(
                        memberForm.getCity(),
                        memberForm.getStreet(),
                        memberForm.getPostcode()))
                .role(Role.USER)
                .build();
    }

    /**
     * 권한 변경
     */
    public void updateRole(Role role) {
        this.role = role;
    }
}
