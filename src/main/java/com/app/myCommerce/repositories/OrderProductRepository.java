package com.app.myCommerce.repositories;

import com.app.myCommerce.schema.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProducts, Long> {
    List<OrderProducts> findByOrderId(Long orderId);
}
