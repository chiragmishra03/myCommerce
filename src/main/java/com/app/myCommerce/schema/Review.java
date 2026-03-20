package com.app.myCommerce.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql="UPDATE reviews set deleted_at = CURRENT_TIMESTAMP where id=?")
@SQLRestriction("deleted_at IS NULL")
@Table(name = "reviews")
public class Review extends BaseEntity{

    private String comment;

    @Column(nullable = false)
    private BigDecimal rating;

    @JoinColumn(name = "order_id",nullable = false)
    @OneToOne(fetch=FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "product_id",nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    private Product product;

}
