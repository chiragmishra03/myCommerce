package com.app.myCommerce.mappers;

import com.app.myCommerce.dto.orders.OrderItemsDto;
import com.app.myCommerce.dto.orders.OrderResponseDto;
import com.app.myCommerce.schema.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "currentStatus", source = "status")
    @Mapping(target= "orderItems",ignore = true)
    OrderResponseDto mapToOrderResponseDto(Order order);

}

