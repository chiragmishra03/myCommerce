package com.app.myCommerce.service;

import com.app.myCommerce.dto.CreateProductRequestDTO;
import com.app.myCommerce.repositories.ProductRepository;
import com.app.myCommerce.schema.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not Found"));
    }

    public Product createProduct(CreateProductRequestDTO requestDTO){
        Product product = Product.builder().
                title(requestDTO.getTitle()).
                description(requestDTO.getDescription()).
                rating(requestDTO.getRating()).
                image(requestDTO.getImage()).
                price(requestDTO.getPrice()).
                units(requestDTO.getUnits()).
                category(requestDTO.getCategory()).
                build();

        return productRepository.save(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<String> getAllUniqueCategories(){
        return productRepository.findCategories();
    }
}
