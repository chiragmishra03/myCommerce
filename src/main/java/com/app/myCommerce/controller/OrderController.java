package com.app.myCommerce.controller;

import com.app.myCommerce.dto.orders.CreateOrderDto;
import com.app.myCommerce.dto.orders.OrderResponseDto;
import com.app.myCommerce.schema.Order;
import com.app.myCommerce.service.OrderService;
import com.app.myCommerce.utilities.api.APIStructure;
import lombok.RequiredArgsConstructor;
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
    public List<Order> getAllOrders() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PostMapping("/create")
    public ResponseEntity<APIStructure<OrderResponseDto>> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        OrderResponseDto orderResponseDto = orderService.createOrder(createOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIStructure.success(orderResponseDto, "Order created successfully"));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented");
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
