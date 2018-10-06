package com.product.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.billing.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {
	
	List<PurchaseOrder> findAllByCompanyIdAndIsDeleted(long companyId,int isDeleted);

}

