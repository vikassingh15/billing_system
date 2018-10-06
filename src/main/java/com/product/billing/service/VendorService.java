package com.product.billing.service;

import java.util.List;

import com.product.billing.dto.VendorDTO;
import com.product.billing.model.Vendor;

public interface VendorService {
    List<Vendor> getVendors();

    List<Vendor> getVendors(long companyId,int isDeleted);

    void save(Vendor vendor);

    VendorDTO getVendorById(long id);
}