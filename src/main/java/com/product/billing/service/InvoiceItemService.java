package com.product.billing.service;

import java.util.List;

import com.product.billing.dto.InvoiceItemDTO;
import com.product.billing.model.InvoiceItem;

public interface InvoiceItemService {

	List<InvoiceItem> getInvoiceItems();

	void save(InvoiceItem invoiceItem);

	InvoiceItemDTO getInvoiceItemById(long id);

}
