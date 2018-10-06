package com.product.billing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.billing.model.ReceiptItems;
import com.product.billing.repository.ReceiptItemsRepository;
import com.product.billing.service.ReceiptItemsService;

@Service
public class ReceiptItemsServiceImpl implements ReceiptItemsService{
	
	@Autowired
	private ReceiptItemsRepository receiptItemsRepository;
	
	public ReceiptItemsServiceImpl(ReceiptItemsRepository receiptItemsRepository) {
		this.receiptItemsRepository=receiptItemsRepository;
	}

	@Override
	public List<ReceiptItems> getReceiptItemsDTO() {
		return this.receiptItemsRepository.findAll();
	}

	@Override
	public void save(ReceiptItems receiptItems) {
		this.receiptItemsRepository.save(receiptItems);
	}

	@Override
	public ReceiptItems getReceiptItemsById(long id) {
		return this.receiptItemsRepository.getOne(id);
	}

	@Override
	public void delete(ReceiptItems receiptItems) {
		this.receiptItemsRepository.delete(receiptItems);
	}

}
