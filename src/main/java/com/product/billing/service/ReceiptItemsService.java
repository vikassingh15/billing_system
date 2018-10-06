package com.product.billing.service;

import java.util.List;

import com.product.billing.model.ReceiptItems;

public interface ReceiptItemsService {
	List<ReceiptItems> getReceiptItemsDTO();
	void save(ReceiptItems receiptItems);
	ReceiptItems getReceiptItemsById(long id);
	void delete(ReceiptItems receiptItems);
}
