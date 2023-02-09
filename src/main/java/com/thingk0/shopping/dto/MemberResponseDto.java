package com.thingk0.shopping.dto;

import com.thingk0.shopping.entity.Role;
import com.thingk0.shopping.entity.embedded.Address;
import lombok.Data;

@Data
public class MemberResponseDto {

    private Long id;
    private String username;
    private Address address;
    private Role role;

}
