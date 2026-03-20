package com.app.myCommerce.mappers;

import com.app.myCommerce.dto.reviews.CreateReviewRequestDTO;
import com.app.myCommerce.dto.reviews.GetReviewResponseDTO;
import com.app.myCommerce.schema.Order;
import com.app.myCommerce.schema.Product;
import com.app.myCommerce.schema.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "orderId", target = "order")
    Review mapToReviewEntity(CreateReviewRequestDTO requestDTO);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "order.id", target = "orderId")
    GetReviewResponseDTO mapToGetReviewResponseDTO(Review review);

    default Product mapProduct(Long productId) {
        if (productId == null) return null;
        Product product = new Product();
        product.setId(productId);
        return product;
    }

    default Order mapOrder(Long orderId) {
        if (orderId == null) return null;
        Order order = new Order();
        order.setId(orderId);
        return order;
    }
}
