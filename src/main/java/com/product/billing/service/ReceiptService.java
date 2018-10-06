package com.product.billing.service;

import java.util.List;

import com.product.billing.dto.ReceiptDTO;
import com.product.billing.model.Receipt;

public interface ReceiptService {
	List<Receipt> getReceipts();
	List<Receipt> getReceipts(long companyId,int isDeleted);
	void save(Receipt receipt);
	ReceiptDTO getReceiptById(long id);
	void delete(Receipt receipt);
}
