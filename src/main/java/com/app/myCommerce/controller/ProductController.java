package com.app.myCommerce.controller;

import com.app.myCommerce.dto.product.create.CreateProductRequestDTO;
import com.app.myCommerce.dto.product.get.GetProductResponseDTO;
import com.app.myCommerce.dto.product.get.GetProductWithDetailsResponseDTO;
import com.app.myCommerce.schema.Product;
import com.app.myCommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        try{
            Product recievedProduct = productService.getProductById(id);
            GetProductResponseDTO responseDTO = GetProductResponseDTO.builder()
                    .title(recievedProduct.getTitle())
                    .description(recievedProduct.getDescription())
                    .id(recievedProduct.getId())
                    .rating(recievedProduct.getRating())
                    .price(recievedProduct.getPrice())
                    .units(recievedProduct.getUnits())
                    .image(recievedProduct.getImage())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequestDTO productRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body( productService.createProduct(productRequestDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductsByCategory(@RequestParam(value = "categoryName",required = false) String category){
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductsByCategory(category));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<?> getDetailsOfProduct(@PathVariable("id") Long id){
        try{
            Product recievedProduct = productService.getProductById(id);
            GetProductWithDetailsResponseDTO responseDTO = GetProductWithDetailsResponseDTO.builder()
                    .title(recievedProduct.getTitle())
                    .description(recievedProduct.getDescription())
                    .id(recievedProduct.getId())
                    .rating(recievedProduct.getRating())
                    .price(recievedProduct.getPrice())
                    .units(recievedProduct.getUnits())
                    .image(recievedProduct.getImage())
                    .category(recievedProduct.getCategory())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
