package com.main.reviews;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.main.company.Company;
import com.main.company.CompanyRepository;
import com.main.company.CompanyService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository  reviewRepository;  
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@Override
	public List<Review> findAllReviews(Long companyId) {
		return reviewRepository.findByCompanyId(companyId);
	}

	@Override
	public boolean AddReviews(Long companyId, Review review) {

		Company com = companyRepository.findById(companyId).orElse(null);
		if(com!= null) {
			review.setCompany(com);
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	
	//very Important API for learn 
	@Override
	public Review getReviewById(Long companyId, Long reviewId) {
		
		 List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		 return reviews.stream().filter(review->review.getId().equals(reviewId)).findFirst().orElse(null);
	}

	@Override
	public boolean UpdateReview(Long companyId, Long reviewId , Review review) {

		if(companyRepository.getCompanyById(companyId)!=null) {
			review.setCompany(companyRepository.getCompanyById(companyId));
			review.setId(reviewId);
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public boolean DeleteReview(Long companyId, Long reviewId) {
		
		if(companyService.findById(companyId) != null && reviewRepository.existsById(reviewId)) {
			
			Review review = reviewRepository.findById(reviewId).orElse(null);
			Company company = review.getCompany();
			company.getReviews().remove(review);
			review.setCompany(null);
			companyService.UpdateCompany(company, companyId);
			reviewRepository.deleteById(reviewId);
			return true;
		}
		return false;
	}
	
}
