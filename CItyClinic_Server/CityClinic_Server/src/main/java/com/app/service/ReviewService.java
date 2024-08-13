package com.app.service;

import java.util.List;

import com.app.dto.ReviewDTO;
import com.app.entity.Review;

public interface ReviewService {

	Review createReview(ReviewDTO reviewDto);

	Review updateReview(Long reviewId, ReviewDTO reviewDto);

	Review getReviewById(Long reviewId);

	List<ReviewDTO> getAllReviews();

	String deleteReview(Long reviewId);

}
