package com.thingk0.shopping.repository;

import com.thingk0.shopping.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
