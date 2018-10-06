package com.product.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.product.billing.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

	List<Vendor> findAllByCompanyIdAndIsDeleted(long companyId,int isDeleted);

}
