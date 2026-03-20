package com.app.myCommerce.service;

import com.app.myCommerce.dto.reviews.CreateReviewRequestDTO;
import com.app.myCommerce.dto.reviews.GetReviewResponseDTO;
import com.app.myCommerce.exceptions.ResourceNotFound;
import com.app.myCommerce.mappers.ReviewMapper;
import com.app.myCommerce.repositories.ReviewRepository;
import com.app.myCommerce.schema.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public List<GetReviewResponseDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(reviewMapper::mapToGetReviewResponseDTO)
                .collect(Collectors.toList());
    }

    public GetReviewResponseDTO createReview(CreateReviewRequestDTO requestDTO) {
        Review review = reviewMapper.mapToReviewEntity(requestDTO);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.mapToGetReviewResponseDTO(savedReview);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFound("Review not found with id " + id);
        }
        reviewRepository.deleteById(id);
    }

    public GetReviewResponseDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Review not found with id " + id));
        return reviewMapper.mapToGetReviewResponseDTO(review);
    }

    public List<GetReviewResponseDTO> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream()
                .map(reviewMapper::mapToGetReviewResponseDTO)
                .collect(Collectors.toList());
    }

    public List<GetReviewResponseDTO> getReviewsByOrderId(Long orderId) {
        List<Review> reviews = reviewRepository.findByOrderId(orderId);
        return reviews.stream()
                .map(reviewMapper::mapToGetReviewResponseDTO)
                .collect(Collectors.toList());
    }
}
