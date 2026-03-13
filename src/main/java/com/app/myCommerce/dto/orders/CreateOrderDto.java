package com.app.myCommerce.dto.orders;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

    private List<OrderItemsDto> items;

}
