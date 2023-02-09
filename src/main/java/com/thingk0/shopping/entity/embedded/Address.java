package com.thingk0.shopping.entity.embedded;

import lombok.Data;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    private String city;        // 도시
    private String street;      // 도로명
    private String postcode;    // 우편번호

}
