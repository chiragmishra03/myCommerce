package com.app.myCommerce.repositories;

import com.app.myCommerce.schema.Order;
import com.app.myCommerce.schema.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
