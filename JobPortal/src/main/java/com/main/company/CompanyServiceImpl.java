package com.main.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl  implements CompanyService{

	
	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company findById(Long id) {

		Company com=companyRepository.findById(id).orElse(null);
		return com;
	}

	@Override
	public boolean AddCompany(Company Com) {

		try {
			companyRepository.save(Com);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean DeleteCompany(Long id) {

		try {
			companyRepository.deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean UpdateCompany(Company com, Long id) {
		Optional<Company> comById = companyRepository.findById(id);
		if(comById.isPresent()) {
			Company company = comById.get();
			company.setName(com.getName());
			company.setDescription(com.getDescription());
			company.setJobs(com.getJobs());
			companyRepository.save(company);
			return true;
		}
		return false;
	}

	
}
