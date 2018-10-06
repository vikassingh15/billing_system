package com.product.billing.service;

import java.util.List;

import com.product.billing.dto.CompanyDTO;
import com.product.billing.model.Company;

public interface CompanyService {
    List<Company> getCompanies();
    
    List<Company> getCompanies(int isDeleted);

    void save(Company company);

    CompanyDTO getCompanyById(long id);
}