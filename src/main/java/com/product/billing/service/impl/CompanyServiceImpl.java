package com.product.billing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.billing.dto.CompanyDTO;
import com.product.billing.model.Company;
import com.product.billing.repository.CompanyRepository;
import com.product.billing.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    
	@Override
	public List<Company> getCompanies() {
		return this.companyRepository.findAll();
	}

	@Override
	public void save(Company company) {
		this.companyRepository.save(company);
	}

	@Override
	public CompanyDTO getCompanyById(long id) {
        return new CompanyDTO(this.companyRepository.getOne(id));
	}

	@Override
	public List<Company> getCompanies(int isDeleted) {
		return this.companyRepository.findAllByIsDeleted(isDeleted);
	}
}
