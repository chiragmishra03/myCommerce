package com.app.myCommerce.controller;

import com.app.myCommerce.dto.product.CreateProductRequestDTO;
import com.app.myCommerce.dto.product.GetProductResponseDTO;
import com.app.myCommerce.dto.product.GetProductWithDetailsResponseDTO;
import com.app.myCommerce.schema.Product;
import com.app.myCommerce.service.ProductService;
import com.app.myCommerce.utilities.api.APIStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get-all")
    public ResponseEntity<APIStructure<List<GetProductResponseDTO>>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(productService.getAllProducts(), "Products fetched successfully" ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIStructure<GetProductResponseDTO>> getProductById(@PathVariable("id") Long id){

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

        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(responseDTO,"Product fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<APIStructure<GetProductResponseDTO>> createProduct(@RequestBody CreateProductRequestDTO productRequestDTO){

        GetProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIStructure.success(createdProduct,"Product created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIStructure<String>> deleteProductById(@PathVariable("id") Long id){

        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success("Deleted Successfully", "Product deleted successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<APIStructure<List<GetProductResponseDTO>>> getProductsByCategory(
            @RequestParam(value = "categoryName",required = false) String category){

        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(productService.getProductsByCategory(category), "Products fetched successfully"));
    }


    @GetMapping("/{id}/details")
    public ResponseEntity<APIStructure<GetProductWithDetailsResponseDTO>> getDetailsOfProduct(@PathVariable("id") Long id){

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

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(APIStructure.success(responseDTO,"Product details fetched successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIStructure<GetProductResponseDTO>> updateProduct(@PathVariable("id") Long id,@RequestBody CreateProductRequestDTO productRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(productService.updateProduct(id,productRequestDTO), "Product updated successfully"));
    }

}
