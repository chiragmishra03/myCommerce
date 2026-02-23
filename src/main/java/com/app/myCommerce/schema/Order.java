package com.app.myCommerce.schema;

import com.app.myCommerce.schema.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order extends BaseEntity{

    @Column(nullable = false)
    private OrderStatus status;
    @Column(nullable = false)
    private BigDecimal finalPrice;

}
