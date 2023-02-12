package com.thingk0.shopping.dto;

import com.thingk0.shopping.entity.embedded.Address;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberForm {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Length(min = 8, max = 15, message = "아이디는 8자 이상, 15자 이하로 입력해주세요.")
    private String username;    // 아이디

    @NotBlank(message = "아이디를 입력해주세요.")
    @Length(min = 8, max = 15, message = "아이디는 8자 이상, 15자 이하로 입력해주세요.")
    private String password;    // 비밀번호

    @NotBlank(message = "도시를 입력해주세요.")
    private String city;        // 도시

    private String street;      // 도로명

    private String postcode;    // 우편번호
}
