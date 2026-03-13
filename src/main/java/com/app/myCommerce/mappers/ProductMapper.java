package com.app.myCommerce.mappers;

import com.app.myCommerce.dto.product.CreateProductRequestDTO;
import com.app.myCommerce.dto.product.GetProductResponseDTO;
import com.app.myCommerce.schema.Category;
import com.app.myCommerce.schema.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "categoryId", target = "category")
    Product mapToProductEntity(CreateProductRequestDTO requestDTO);

    GetProductResponseDTO mapToCreateProductReqDto(Product product);

    default Category mapCategory(Long categoryId) {
        if (categoryId == null) return null;

        Category category = new Category();
        category.setId(categoryId);
        return category;
    }
}
