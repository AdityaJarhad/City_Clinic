package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.ReviewDTO;
import com.app.entity.Review;
import com.app.service.ReviewService;

@CrossOrigin(origins ="http://localhost:5173")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDTO reviewDto) {
        Review newReview = reviewService.createReview(reviewDto);
        return ResponseEntity.ok(newReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDto) {
        Review updatedReview = reviewService.updateReview(id, reviewDto);
        return ResponseEntity.ok(updatedReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        String response = reviewService.deleteReview(id);
        return ResponseEntity.ok(response);
    }
}
