package com.app.myCommerce.repositories;

import com.app.myCommerce.dto.orders.OrderItemsDto;
import com.app.myCommerce.schema.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProducts, Long> {
    @Query(""" 
            SELECT o FROM OrderProducts o WHERE o.order.id IN :orderIds
            """)
    List<OrderProducts> findByOrderIds(@Param("orderIds") List<Long> orderIds);
}
