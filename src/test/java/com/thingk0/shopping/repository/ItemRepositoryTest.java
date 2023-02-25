package com.thingk0.shopping.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thingk0.shopping.entity.Item;
import com.thingk0.shopping.entity.ItemStatus;
import com.thingk0.shopping.entity.QItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("상품 생성 테스트")
    public void createItem() throws Exception {
        // given
        Item item = itemCreate();

        // when
        Item savedItem = itemRepository.save(item);

        // then
        assertThat(savedItem.getItemName()).isEqualTo(item.getItemName());      // 이름이 같은가??
        assertThat(savedItem.getPrice()).isEqualTo(item.getPrice());    // 가격이 같은가??
    }

    @Test
    @DisplayName("검색 테스트")
    public void findByNameTest() throws Exception {
        // given
        this.itemListCreate(10);
        // when
        List<Item> byName = itemRepository.findByName("상품 5");
        for (Item item : byName) {
            System.out.println("item = " + item);
        }
        System.out.println("======================================================");
        List<Item> byNameInclusive = itemRepository.findByNameInclusive("상품");
        for (Item item : byNameInclusive) {
            System.out.println("item = " + item);
        }
        System.out.println("======================================================");
        List<Item> byNameFrontInclusive = itemRepository.findByNameFrontInclusive("상");
        for (Item item : byNameFrontInclusive) {
            System.out.println("item = " + item);
        }
        System.out.println("======================================================");
        List<Item> byNameBackInclusive = itemRepository.findByNameBackInclusive("3");
        for (Item item : byNameBackInclusive) {
            System.out.println("item = " + item);
        }

    }

    private void itemListCreate(int n) {
        while (n-- > 0) {
            itemRepository.save(Item.builder()
                    .itemName("상품 " + (n+1))
                    .price(10000 + (n+1))
                    .itemDetail("상품 디테일 " + (n+1))
                    .itemStatus(ItemStatus.SALE)
                    .stockQuantity(10 * (n+1))
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());
        }
    }

    private void itemListCreate2(int n) {
        while (n-- > 0) {
            itemRepository.save(Item.builder()
                    .itemName("A상품 " + (n+1))
                    .price(10000 + (n+1))
                    .itemDetail("상품 디테일 " + (n+1))
                    .itemStatus(ItemStatus.SALE)
                    .stockQuantity(10 * (n+1))
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());

            itemRepository.save(Item.builder()
                    .itemName("B상품 " + (n+1))
                    .price(10000 + (n+1))
                    .itemDetail("상품 디테일 " + (n+1))
                    .itemStatus(ItemStatus.SOLD_OUT)
                    .stockQuantity(10 * (n+1))
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());
        }
    }

    private static Item itemCreate() {
        return Item.builder()
                .itemName("아이폰14pro")
                .price(10000)
                .itemDetail("머리부터 발끝까지 완벽한 아이뻐14")
                .itemStatus(ItemStatus.SALE)
                .stockQuantity(10)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("QueryDSL 1")
    public void querydslTest() throws Exception {
        this.itemListCreate(5);

        QItem item = QItem.item;
        JPAQueryFactory query = new JPAQueryFactory(em);

        // QueryDSL 로 리스트 조회.
        List<Item> itemList = query.select(item)
                .from(item)
                .where(item.itemStatus.eq(ItemStatus.SALE))
                .where(item.itemDetail.like("%" + "상품" + "%"))
                .fetch();

        for (Item i : itemList) {
            System.out.println("i = " + i);
        }
    }

    @Test
    @DisplayName("QueryDSL 2")
    public void querydslTest2() throws Exception {
        // given
        this.itemListCreate2(10);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;

        String itemDetail = "디테일";
        String itemName = "A상품";
        String itemStatus = "SALE";

        booleanBuilder
                .and(item.itemDetail.like("%" + itemDetail + "%"))
                .and(item.itemName.startsWith(itemName));

        if (StringUtils.equals(itemStatus, ItemStatus.SALE)) {
            booleanBuilder.and(item.itemStatus.eq(ItemStatus.SALE));
        }

        Pageable pageable = PageRequest.of(0, 5);
        // when
        Page<Item> itemPage = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("itemPage.getTotalElements() = " + itemPage.getTotalElements());

        // then
        List<Item> itemList = itemPage.getContent();
        for (Item i : itemList) {
            System.out.println(i);
        }
    }

}