package com.thingk0.shopping.repository;

import com.thingk0.shopping.entity.Item;
import com.thingk0.shopping.entity.ItemStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 생성 테스트")
    public void createItem() throws Exception {
        // given
        Item item = Item.builder()
                .name("아이폰14pro")
                .price(10000)
                .itemDetail("머리부터 발끝까지 완벽한 아이뻐14")
                .itemStatus(ItemStatus.SALE)
                .stockQuantity(10)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        // when
        Item savedItem = itemRepository.save(item);

        // then
        assertThat(savedItem.getName()).isEqualTo(item.getName());      // 이름이 같은가??
        assertThat(savedItem.getPrice()).isEqualTo(item.getPrice());    // 가격이 같은가??
    }

}