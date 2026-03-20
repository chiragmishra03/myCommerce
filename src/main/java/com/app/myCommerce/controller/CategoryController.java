package com.app.myCommerce.controller;

import com.app.myCommerce.dto.categories.CreateCategoryRequestDTO;
import com.app.myCommerce.schema.Category;
import com.app.myCommerce.service.CategoryService;
import com.app.myCommerce.utilities.api.APIStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get-all")
    public ResponseEntity<APIStructure<List<Category>>> getAllCategories() {

        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(categories, "Categories fetched successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIStructure<Category>> getCategoryById(@PathVariable("id") Long id) {

        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(category, "Category fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<APIStructure<Category>> createCategory(
            @RequestBody CreateCategoryRequestDTO categoryRequestDTO) {

        Category createdCategory = categoryService.createCategory(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIStructure.success(createdCategory, "Category created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIStructure<String>> deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success("Category deleted successfully", "Delete operation completed"));
    }

    @GetMapping("/categories/all")
    public ResponseEntity<APIStructure<List<String>>> getAllUniqueCategories() {

        List<String> categories = categoryService.getAllUniqueCategories();
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(categories, "Unique categories fetched successfully"));
    }
}