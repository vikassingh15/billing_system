package com.product.billing.service.impl;

import com.product.billing.dto.PurchaseOrderDTO;
import com.product.billing.model.PurchaseOrder;
import com.product.billing.repository.PurchaseOrderRepository;
import com.product.billing.service.PurchaseOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

	@Override
	public List<PurchaseOrder> gePurchaseOrders() {
		return purchaseOrderRepository.findAll();
	}

	@Override
	public void save(PurchaseOrder purchaseOrder) {
		purchaseOrderRepository.save(purchaseOrder);
	}

	@Override
	public PurchaseOrderDTO getPurchaseOrderById(long id) {
		return new PurchaseOrderDTO(this.purchaseOrderRepository.getOne(id));
	}

	@Override
	public void delete(PurchaseOrder purchaseOrder) {
		this.purchaseOrderRepository.delete(purchaseOrder);
	}

	@Override
	public List<PurchaseOrder> gePurchaseOrders(long companyId,int isDeleted) {
		return this.purchaseOrderRepository.findAllByCompanyIdAndIsDeleted(companyId,isDeleted);
	}
}	
