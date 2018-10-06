package com.product.billing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.billing.dto.InvoiceDTO;
import com.product.billing.model.Invoice;
import com.product.billing.repository.InvoiceRepository;
import com.product.billing.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public void save(Invoice invoice) {
        this.invoiceRepository.save(invoice);
    }

	@Override
	public InvoiceDTO getInvoiceById(long id) {
		return new InvoiceDTO(this.invoiceRepository.getOne(id));
	}

	@Override
	public void delete(Invoice invoice) {
		this.invoiceRepository.delete(invoice);		
	}

	@Override
	public List<Invoice> getInvoices(long companyId,int isDeleted) {
		return this.invoiceRepository.findAllByCompanyIdAndIsDeleted(companyId, isDeleted);
	}

}
