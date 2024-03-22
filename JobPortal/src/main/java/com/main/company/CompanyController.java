package com.main.company;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService companyService;

	
	@GetMapping("/GetAllCompanies")
	public ResponseEntity<List<Company>> findAll(){
		
		return new ResponseEntity<>(companyService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/GetAllCompanies/{id}")
	public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
		
		 Company comById = companyService.findById(id);
		 if(comById!= null) {
			 return new ResponseEntity<>(comById,HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/AddCompany")
	public ResponseEntity<String> AddCompany(@RequestBody Company com) {
		
		boolean result = companyService.AddCompany(com);
		if(result) {
			return new ResponseEntity<>("Company Added Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Company Not Added Successfully",HttpStatus.NOT_FOUND);
	
	}
	
	@DeleteMapping("/DeleteCompany/{id}")
	public ResponseEntity<String> DeleteCompany(@PathVariable("id") Long id) {
		
		boolean result = companyService.DeleteCompany(id);
		if(result) {
			return new ResponseEntity<>("Company Deleted Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Company Not Deleted Successfully",HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/UpdateCompany/{id}")
	public ResponseEntity<String> UpdateCompany(@RequestBody Company com, @PathVariable("id") Long id){
		
		boolean result = companyService.UpdateCompany(com,id);
		if(result) {
			return new ResponseEntity<>("Company Updated Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Company Not Updated Successfully",HttpStatus.NOT_FOUND);

	}
	
}
