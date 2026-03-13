package com.app.myCommerce.dto.orders;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsDto {

    private Long productId;
    private Integer quantity;

}
