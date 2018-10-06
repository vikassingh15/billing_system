package com.product.billing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.billing.dto.ReceiptDTO;
import com.product.billing.model.Receipt;
import com.product.billing.repository.ReceiptRepository;
import com.product.billing.service.ReceiptService;

@Service
public class ReceiptServiceImpl implements ReceiptService{
	
	private final ReceiptRepository receiptRepository;

	@Autowired
	public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
	     this.receiptRepository = receiptRepository;
	}

	@Override
	public List<Receipt> getReceipts() {
		return this.receiptRepository.findAll();
	}

	@Override
	public void save(Receipt receipt) {
		this.receiptRepository.save(receipt); 
	}

	@Override
	public ReceiptDTO getReceiptById(long id) {
		return new ReceiptDTO(this.receiptRepository.getOne(id));
	}

	@Override
	public void delete(Receipt receipt) {
		this.receiptRepository.delete(receipt);
	}

	@Override
	public List<Receipt> getReceipts(long companyId,int isDeleted) {
		return this.receiptRepository.findAllByCompanyIdAndIsDeleted(companyId, isDeleted);
	}

}
