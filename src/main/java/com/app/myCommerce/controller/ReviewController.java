package com.app.myCommerce.controller;

import com.app.myCommerce.dto.reviews.CreateReviewRequestDTO;
import com.app.myCommerce.dto.reviews.GetReviewResponseDTO;
import com.app.myCommerce.service.ReviewService;
import com.app.myCommerce.utilities.api.APIStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<APIStructure<List<GetReviewResponseDTO>>> getAllReviews() {
        List<GetReviewResponseDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(reviews, "Reviews fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<APIStructure<GetReviewResponseDTO>> createReview(@RequestBody CreateReviewRequestDTO requestDTO) {
        GetReviewResponseDTO review = reviewService.createReview(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIStructure.success(review, "Review created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIStructure<String>> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success("Review deleted successfully", "Deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIStructure<GetReviewResponseDTO>> getReviewById(@PathVariable Long id) {
        GetReviewResponseDTO review = reviewService.getReviewById(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(review, "Review fetched successfully"));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<APIStructure<List<GetReviewResponseDTO>>> getReviewsByProductId(@PathVariable Long productId) {
        List<GetReviewResponseDTO> reviews = reviewService.getReviewsByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(reviews, "Reviews fetched successfully"));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<APIStructure<List<GetReviewResponseDTO>>> getReviewsByOrderId(@PathVariable Long orderId) {
        List<GetReviewResponseDTO> reviews = reviewService.getReviewsByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(APIStructure.success(reviews, "Reviews fetched successfully"));
    }

}
