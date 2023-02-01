package com.thingk0.shopping.repository;

import com.thingk0.shopping.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
