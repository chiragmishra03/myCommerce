package com.app.myCommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetProductResponseDTO {

    protected Long id;

    protected String title;

    protected String description;

    protected Integer price;

    protected String image;

    protected BigDecimal rating;

    protected Integer units;

}
