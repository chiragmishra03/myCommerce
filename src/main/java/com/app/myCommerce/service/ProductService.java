package com.app.myCommerce.service;

import com.app.myCommerce.dto.product.create.CreateProductRequestDTO;
import com.app.myCommerce.dto.product.get.GetProductResponseDTO;
import com.app.myCommerce.repositories.ProductRepository;
import com.app.myCommerce.schema.Category;
import com.app.myCommerce.schema.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<GetProductResponseDTO> getAllProducts(){
        List<Product> products =  productRepository.findAll();

        List<GetProductResponseDTO> responseDTOS = new ArrayList<>();

        for(Product product:products){
            GetProductResponseDTO responseDTO = GetProductResponseDTO.builder()
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .id(product.getId())
                    .rating(product.getRating())
                    .price(product.getPrice())
                    .units(product.getUnits())
                    .image(product.getImage())
                    .build();
            responseDTOS.add(responseDTO);
        }
        return responseDTOS;
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not Found"));
    }

    public GetProductResponseDTO createProduct(CreateProductRequestDTO requestDTO){

        Category category = categoryService.getCategoryById(requestDTO.getCategoryId());

        Product product = Product.builder().
                title(requestDTO.getTitle()).
                description(requestDTO.getDescription()).
                rating(requestDTO.getRating()).
                image(requestDTO.getImage()).
                price(requestDTO.getPrice()).
                units(requestDTO.getUnits()).
                category(category).
                build();

        Product savedProduct = productRepository.save(product);

        GetProductResponseDTO getProductResponseDTO = GetProductResponseDTO.builder() .
                title(savedProduct.getTitle()).
                description(savedProduct.getDescription()).
                price(savedProduct.getPrice()).
                image(savedProduct.getImage()).
                rating(savedProduct.getRating()).
                units(savedProduct.getUnits()).build();

        return getProductResponseDTO;
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

}
