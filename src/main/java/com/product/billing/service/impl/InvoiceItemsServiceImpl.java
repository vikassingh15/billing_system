package com.product.billing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.billing.dto.InvoiceItemDTO;
import com.product.billing.model.InvoiceItem;
import com.product.billing.repository.InvoiceItemsRepository;
import com.product.billing.service.InvoiceItemService;

@Service
public class InvoiceItemsServiceImpl implements InvoiceItemService {

	private final InvoiceItemsRepository invoiceItemsRepository;

    @Autowired
    public InvoiceItemsServiceImpl(InvoiceItemsRepository invoiceItemsRepository) {
        this.invoiceItemsRepository = invoiceItemsRepository;
    }

    @Override
    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItemsRepository.findAll();
    }

    @Override
    public void save(InvoiceItem invoiceItem) {
    	this.invoiceItemsRepository.save(invoiceItem);
    }

    @Override
    public InvoiceItemDTO getInvoiceItemById(long id) {
        return new InvoiceItemDTO();
    }
}
