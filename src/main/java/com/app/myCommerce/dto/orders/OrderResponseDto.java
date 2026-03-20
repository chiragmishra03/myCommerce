package com.app.myCommerce.dto.orders;

import com.app.myCommerce.schema.Order;
import com.app.myCommerce.schema.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {


    private Integer totalValue;
    private OrderStatus currentStatus;
    private Long orderId;
    private List<OrderItemsDto> orderItems;

}
