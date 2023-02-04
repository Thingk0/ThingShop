package com.thingk0.shopping.repository;

import com.thingk0.shopping.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //=== 검색어와 일치하는 상품찾기 ===//
    @Query("select i from Item i where i.name = :itemName")
    List<Item> findByName(@Param("itemName") String name);
    //=== 검색어를 포함하는 상품찾기 ===//
    @Query("select i from Item i where i.name like %:itemName%")
    List<Item> findByNameInclusive(@Param("itemName") String name);
    //=== 검색어로 시작하는 상품찾기 ===//
    @Query("select i from Item i where i.name like :itemName%")
    List<Item> findByNameFrontInclusive(@Param("itemName") String name);
    //=== 검색어로 끝나는 상품찾기 ===//
    @Query("select i from Item i where i.name like %:itemName")
    List<Item> findByNameBackInclusive(@Param("itemName") String name);
}
