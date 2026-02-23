package com.app.myCommerce.controller;

import com.app.myCommerce.dto.categories.create.CreateCategoryRequestDTO;
import com.app.myCommerce.schema.Category;
import com.app.myCommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
        try{
            Category recievedCategory = categoryService.getCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(recievedCategory);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequestDTO categoryRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body( categoryService.createCategory(categoryRequestDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
    }

}
