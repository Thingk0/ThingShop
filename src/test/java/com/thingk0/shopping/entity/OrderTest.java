package com.thingk0.shopping.entity;

import com.thingk0.shopping.dto.ItemForm;
import com.thingk0.shopping.repository.ItemRepository;
import com.thingk0.shopping.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@SpringBootTest
@Transactional
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void itemTest() {
        Order order = new Order();

        for (int i = 0; i < 3; i++) {
            Item item = itemCreate();
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItemList().add(orderItem);
        }

        orderRepository.save(order);

        em.flush();
        em.clear();
    }

    private static Item itemCreate() {
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("상품 상세설명");
        item.setItemStatus(ItemStatus.SALE);
        item.setStockQuantity(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        return item;
    }
}