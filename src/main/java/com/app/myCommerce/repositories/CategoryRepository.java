package com.app.myCommerce.repositories;

import com.app.myCommerce.schema.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(nativeQuery = true,value="SELECT DISTINCT category FROM PRODUCTS")
    List<String> findCategories();
}
