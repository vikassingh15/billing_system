package com.product.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.billing.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	List<Invoice> findAllByCompanyIdAndIsDeleted(long companyId,int isDeleted);

}
