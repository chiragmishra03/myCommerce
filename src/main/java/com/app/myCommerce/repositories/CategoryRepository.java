package com.app.myCommerce.repositories;

import com.app.myCommerce.schema.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
