package com.app.myCommerce.dto;

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
    private BigDecimal price;
    private String image;
    private Long categoryId;
    private String rating;
    private BigDecimal units;

}
