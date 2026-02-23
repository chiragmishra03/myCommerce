package com.app.myCommerce.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED )
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    private String image;
    @Column(nullable = false)
    private String category;
    private String rating;
    @Column(nullable = false)
    private BigDecimal units;


}
