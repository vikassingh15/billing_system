package com.product.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.billing.model.ReceiptItems;

public interface ReceiptItemsRepository extends JpaRepository<ReceiptItems, Long>{

}
