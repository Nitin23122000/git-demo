package com.main.reviews;

import java.util.List;

public interface ReviewService {
	
	List<Review> findAllReviews(Long companyId);
	boolean AddReviews(Long companyId,Review review);
	Review getReviewById(Long companyId,Long reviewId);
	boolean UpdateReview(Long companyId,Long reviewId, Review review);
	boolean DeleteReview(Long companyId,Long reviewId);

}
