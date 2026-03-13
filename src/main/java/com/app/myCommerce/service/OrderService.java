package com.app.myCommerce.service;

import com.app.myCommerce.dto.orders.CreateOrderDto;
import com.app.myCommerce.dto.orders.OrderItemsDto;
import com.app.myCommerce.dto.orders.OrderResponseDto;
import com.app.myCommerce.exceptions.ResourceNotFound;
import com.app.myCommerce.mappers.OrderMapper;
import com.app.myCommerce.repositories.OrderProductRepository;
import com.app.myCommerce.repositories.OrderRepository;
import com.app.myCommerce.repositories.ProductRepository;
import com.app.myCommerce.schema.Order;
import com.app.myCommerce.schema.OrderProducts;
import com.app.myCommerce.schema.Product;
import com.app.myCommerce.schema.enums.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final OrderMapper orderMapper;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @Transactional
    public OrderResponseDto createOrder(CreateOrderDto dto) {

        Order order = Order.builder()
                .status(OrderStatus.PENDING)
                .totalValue(0)
                .build();

        orderRepository.save(order);

        List<Long> productIds = dto.getItems()
                .stream()
                .map(OrderItemsDto::getProductId)
                .toList();

        Map<Long, Product> productMap = productRepository.findAllById(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        int totalValue = 0;

        for (OrderItemsDto item : dto.getItems()) {

            Product product = productMap.get(item.getProductId());

            if (product == null) {
                throw new ResourceNotFound("Product not found with id " + item.getProductId());
            }

            if (item.getQuantity() <= 0) {
                throw new RuntimeException("Quantity must be greater than 0");
            }

            if (product.getUnits() < item.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product id " + item.getProductId()
                );
            }

            productService.reduceUnits(product.getId(), item.getQuantity());

            OrderProducts orderProduct = OrderProducts.builder()
                    .order(order)
                    .product(product)
                    .quantity(item.getQuantity())
                    .build();

            orderProductRepository.save(orderProduct);

            totalValue += product.getPrice() * item.getQuantity();
        }

        order.setTotalValue(totalValue);

        Order savedOrder = orderRepository.save(order);

        OrderResponseDto response = orderMapper.mapToOrderResponseDto(savedOrder);
        response.setOrderItems(dto.getItems());

        return response;
    }



}
