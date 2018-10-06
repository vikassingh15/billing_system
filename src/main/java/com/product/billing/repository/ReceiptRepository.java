package com.product.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.billing.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

	List<Receipt> findAllByCompanyIdAndIsDeleted(long companyId,int isDeleted);

}
