package com.thingk0.shopping.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // PK

    @Column(name = "item_name", nullable = false, length = 20, unique = true)
    private String name;                // 상품 이름

    @Column(name = "price", nullable = false)
    private int price;                  // 상품 가격

    @Lob
    @Column(nullable = false)
    private String itemDetail;          // 상품 설명

    @Column(nullable = false)
    private int stockQuantity;          // 상품 재고수량

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;      // 상품 상태

    private LocalDateTime regTime;      // 상품 등록시간

    private LocalDateTime updateTime;   // 상품 수정시간
}
