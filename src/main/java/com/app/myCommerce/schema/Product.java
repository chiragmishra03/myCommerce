package com.app.myCommerce.schema;

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
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String image;

    private String rating;

    @Column(nullable = false)
    private BigDecimal units;


    @JoinColumn(name = "category_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
