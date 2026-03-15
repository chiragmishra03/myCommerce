package com.app.myCommerce.service;

import com.app.myCommerce.dto.orders.CreateOrderDto;
import com.app.myCommerce.dto.orders.OrderItemsDto;
import com.app.myCommerce.dto.orders.OrderResponseDto;
import com.app.myCommerce.dto.product.GetProductResponseDTO;
import com.app.myCommerce.exceptions.ResourceNotFound;
import com.app.myCommerce.mappers.OrderMapper;
import com.app.myCommerce.mappers.ProductMapper;
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

import java.util.ArrayList;
import java.util.HashMap;
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
    private final ProductMapper productMapper;

    public List<OrderResponseDto> getAllOrders(boolean orderItems) {

        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> responses = new ArrayList<>();

        Map<Long, List<OrderProducts>> itemsMap = new HashMap<>();

        if (orderItems) {
            List<Long> orderIds = orders.stream()
                    .map(Order::getId)
                    .toList();

            List<OrderProducts> items = orderProductRepository.findByOrderIds(orderIds);

            itemsMap = items.stream()
                    .collect(Collectors.groupingBy(op -> op.getOrder().getId()));
        }

        for (Order order : orders) {

            OrderResponseDto dto = orderMapper.mapToOrderResponseDto(order);

            if (orderItems) {

                List<OrderItemsDto> itemDtos = itemsMap
                        .getOrDefault(order.getId(), List.of())
                        .stream()
                        .map(op -> new OrderItemsDto(
                                op.getProduct().getId(),
                                op.getQuantity()))
                        .toList();

                dto.setOrderItems(itemDtos);
            }

            responses.add(dto);
        }

        return responses;
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


    public OrderResponseDto getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Order with Id " + id + " Not found"));

        OrderResponseDto response = orderMapper.mapToOrderResponseDto(order);

        List<OrderProducts> orderProducts = orderProductRepository.findByOrderIds(List.of(order.getId()));

        List<OrderItemsDto> items = orderProducts.stream()
                .map(op -> OrderItemsDto.builder()
                        .productId(op.getProduct().getId())
                        .quantity(op.getQuantity())
                        .build())
                .toList();

        response.setOrderItems(items);

        return response;
    }

    public void deleteBy(Long id) {
        orderRepository.deleteById(id);
    }
}
