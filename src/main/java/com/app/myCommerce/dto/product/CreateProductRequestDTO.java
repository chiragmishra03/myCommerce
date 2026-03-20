package com.app.myCommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestDTO {

    private String title;
    private String description;
    private Integer price;
    private String image;
    private Long categoryId;
    private BigDecimal rating;
    private Integer units;

}
