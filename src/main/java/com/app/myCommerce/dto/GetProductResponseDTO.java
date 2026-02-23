package com.app.myCommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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

    protected BigDecimal price;

    protected String image;

    protected String rating;

    protected BigDecimal units;

}
