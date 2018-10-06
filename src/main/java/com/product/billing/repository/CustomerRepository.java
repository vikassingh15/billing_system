package com.product.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.billing.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findAllByCompanyIdAndIsDeleted(long companyId,int isDeleted);
}
