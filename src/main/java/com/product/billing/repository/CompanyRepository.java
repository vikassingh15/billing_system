package com.product.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.billing.model.Company;
public interface CompanyRepository extends JpaRepository<Company, Long> {

	List<Company> findAllByIsDeleted(int isDeleted);

}