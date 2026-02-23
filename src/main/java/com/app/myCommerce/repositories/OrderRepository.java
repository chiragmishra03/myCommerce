package com.app.myCommerce.repositories;

import com.app.myCommerce.schema.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}