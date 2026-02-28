package com.app.myCommerce.schema;

import com.app.myCommerce.schema.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
@SQLDelete(sql="UPDATE categories set deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at IS NULL")
public class Order extends BaseEntity{

    @Column(nullable = false)
    private OrderStatus status;
    @Column(nullable = false)
    private BigDecimal finalPrice;

}
