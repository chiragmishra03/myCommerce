package com.app.myCommerce.controller;

import com.app.myCommerce.schema.Order;
import com.app.myCommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public List<Order> getAllOrders() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PostMapping
    public Order createOrder() {
        throw new UnsupportedOperationException("Not implemented");
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
