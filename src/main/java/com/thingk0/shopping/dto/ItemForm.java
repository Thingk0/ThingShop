package com.thingk0.shopping.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ItemForm {

    @NotBlank(message = "상품명을 적어주세요.")
    private String itemName;            // 상품 이름
    private int price;                  // 상품 가격
    private String itemDetail;          // 상품 설명
    private int stockQuantity;          // 상품 재고수량

}
