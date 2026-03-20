package com.app.myCommerce.service;

import com.app.myCommerce.dto.categories.CreateCategoryRequestDTO;
import com.app.myCommerce.exceptions.ResourceNotFound;
import com.app.myCommerce.repositories.CategoryRepository;
import com.app.myCommerce.schema.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDTO requestDTO){
        Category newCategory = Category.builder().
                name(requestDTO.getName())
                .build();
        return categoryRepository.save(newCategory);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Category Not Found with id: "+id));
    }

    public void deleteCategoryById(Long id){
        Category foundCategory = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Category Not Found with id: "+id));
        categoryRepository.delete(foundCategory);
    }

    public List<String> getAllUniqueCategories(){
        return categoryRepository.findCategories();
    }
}
