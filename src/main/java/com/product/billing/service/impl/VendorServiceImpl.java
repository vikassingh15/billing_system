package com.product.billing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.billing.dto.VendorDTO;
import com.product.billing.model.Vendor;
import com.product.billing.repository.VendorRepository;
import com.product.billing.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService {
	
	private final VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

	@Override
	public List<Vendor> getVendors() {
		return this.vendorRepository.findAll(); 
	}

	@Override
	public void save(Vendor vendor) {
		this.vendorRepository.save(vendor);
	}

	@Override
	public VendorDTO getVendorById(long id) {
		return new VendorDTO(this.vendorRepository.getOne(id));
	}

	@Override
	public List<Vendor> getVendors(long companyId,int isDeleted) {
		return this.vendorRepository.findAllByCompanyIdAndIsDeleted(companyId,isDeleted);
	}

}
