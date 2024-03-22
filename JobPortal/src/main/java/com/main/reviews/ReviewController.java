package com.main.reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Company/{companyId}")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/GetAllReviews")
	public ResponseEntity<List<Review>> findAllReviews(@PathVariable("companyId") Long companyId){
		return new ResponseEntity<>(reviewService.findAllReviews(companyId),HttpStatus.OK);
	}
	
	@PostMapping("/AddReviews")
	public ResponseEntity<String> AddReviews(@PathVariable("companyId") Long companyId,@RequestBody Review review){
		boolean status = reviewService.AddReviews(companyId,review);
		if(status) {
			return new ResponseEntity<>("Review Created Successfully",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Review Not Added Successfully",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/GetReview/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable("companyId") Long companyId,@PathVariable("reviewId") Long reviewId ){
		 
		Review reviewResult = reviewService.getReviewById(companyId,reviewId);
		if(reviewResult!= null) {
			return new ResponseEntity<>(reviewResult,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	}
	
	@PutMapping("/UpdateReview/{reviewId}")
	public ResponseEntity<String> UpdateReview(@PathVariable("companyId") Long companyId,@PathVariable("reviewId") Long reviewId,@RequestBody Review review ){
		
		boolean result = reviewService.UpdateReview(companyId,reviewId,review);
		if(result) {
			return new ResponseEntity<>("Review Updated Sucessfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Review Not Updated",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/DeleteReview/{reviewId}")
	public ResponseEntity<String> DeleteReview(@PathVariable Long companyId,@PathVariable Long reviewId) {
		
		boolean result = reviewService.DeleteReview(companyId,reviewId);
		if(result) {
			return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Review Not Deleted Successfully",HttpStatus.NOT_FOUND);
	}
}
