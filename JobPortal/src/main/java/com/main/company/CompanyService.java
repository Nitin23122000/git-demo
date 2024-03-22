package com.main.company;

import java.util.List;

public interface CompanyService {

	List<Company> findAll();
	Company findById(Long id);
	boolean AddCompany(Company Com);
	boolean DeleteCompany(Long id);
	boolean UpdateCompany(Company com,Long id);
}
