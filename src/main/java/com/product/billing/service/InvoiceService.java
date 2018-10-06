package com.product.billing.service;

import java.util.List;

import com.product.billing.dto.InvoiceDTO;
import com.product.billing.model.Invoice;

public interface InvoiceService {
    List<Invoice> getInvoices();
    List<Invoice> getInvoices(long companyId,int isDeleted);
    void save(Invoice invoice);
    InvoiceDTO getInvoiceById(long id);
    void delete(Invoice invoice);
}
