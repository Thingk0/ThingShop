package com.thingk0.shopping.entity;

import com.thingk0.shopping.dto.ItemForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@ToString(of = {"id", "name", "price"})
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;                    // PK

    @Column(nullable = false, length = 20)
    private String itemName;                // 상품 이름

    @Column(name = "price", nullable = false)
    private int price;                  // 상품 가격

    @Lob
    @Column(nullable = false)
    private String itemDetail;          // 상품 설명

    private int stockQuantity;          // 상품 재고수량

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;      // 상품 상태

    private LocalDateTime regTime;      // 상품 등록시간

    private LocalDateTime updateTime;   // 상품 수정시간

    public static Item createItem(ItemForm itemForm) {
        return Item
                .builder()
                .itemName(itemForm.getItemName())
                .price(itemForm.getPrice())
                .itemDetail(itemForm.getItemDetail())
                .stockQuantity(itemForm.getStockQuantity())
                .itemStatus(ItemStatus.SALE)
                .build();
    }

    //=== 편의 메서드 ===//
//    @PostConstruct
//    public void setRegTime() {
//        this.regTime = LocalDateTime.now();
//    }

//    public void setUpdateTime(LocalDateTime updateTime) {
//        this.updateTime = updateTime;
//    }
}
