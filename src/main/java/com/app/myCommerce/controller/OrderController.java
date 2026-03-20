package com.app.myCommerce.controller;

import com.app.myCommerce.dto.orders.CreateOrderDto;
import com.app.myCommerce.dto.orders.OrderResponseDto;
import com.app.myCommerce.schema.Order;
import com.app.myCommerce.service.OrderService;
import com.app.myCommerce.utilities.api.APIStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/get-all")
    public ResponseEntity<APIStructure<List<OrderResponseDto>>> getAllOrders(@RequestParam(value = "orderItems",defaultValue = "true",required = false)boolean orderItems) {
        List<OrderResponseDto> orderResponseDtoList = orderService.getAllOrders(orderItems);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(orderResponseDtoList,"Orders fetched successfully"));
    }

    @PostMapping("/create")
    public ResponseEntity<APIStructure<OrderResponseDto>> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        OrderResponseDto orderResponseDto = orderService.createOrder(createOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIStructure.success(orderResponseDto, "Order created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIStructure<String>> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success("Order with Id "+id+" deleted","Deleted Successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIStructure<OrderResponseDto>> getOrderById(@PathVariable("id") Long id) {
        OrderResponseDto orderResponseDto = orderService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(orderResponseDto,"Order with id "+id+" fetched successfully"));
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/{id}/summary")
    public void getOrderSummary(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
