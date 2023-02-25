package com.thingk0.shopping.repository;

import com.thingk0.shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
