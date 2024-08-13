package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ReviewDTO;
import com.app.entity.Review;
import com.app.repository.IReviewRepository;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private IReviewRepository reviewRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Review createReview(ReviewDTO reviewDto) {
		Review review = mapper.map(reviewDto, Review.class);
				
		return reviewRepo.save(review);
	}

	  @Override
	    public Review updateReview(Long reviewId, ReviewDTO reviewDto) {
	        Review review = reviewRepo.findById(reviewId).orElseThrow();
	        mapper.map(reviewDto, review);
	        return reviewRepo.save(review);
	    }

	    @Override
	    public Review getReviewById(Long reviewId) {
	        return reviewRepo.findById(reviewId).orElseThrow();
	    }

	    @Override
	    public List<ReviewDTO> getAllReviews() {
	        return reviewRepo.findAll()
	                .stream()
	                .map(review -> mapper.map(review, ReviewDTO.class))
	                .collect(Collectors.toList());
	    }

	    @Override
	    public String deleteReview(Long reviewId) {
	        Review review = reviewRepo.findById(reviewId).orElseThrow();
	        reviewRepo.delete(review);
	        return "Review deleted: " + review.getId();
	    }
	}