package com.app.myCommerce.repositories;

import com.app.myCommerce.schema.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Name(String categoryName);

}
