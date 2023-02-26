package com.thingk0.shopping.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int count;
    private int orderPrice;

    // 연관관계 편의 메서드
    public void updateOrder(Order order) {
        if (this.order != null) {
            this.order.getOrderItemList().remove(this);
        }
        this.order = order;
        order.getOrderItemList().add(this);
    }
}
