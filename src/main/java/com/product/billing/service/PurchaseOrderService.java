package com.product.billing.service;

import com.product.billing.dto.PurchaseOrderDTO;
import com.product.billing.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrder> gePurchaseOrders();
    List<PurchaseOrder> gePurchaseOrders(long companyId,int isDeleted);
    void save(PurchaseOrder purchaseOrder);
    PurchaseOrderDTO getPurchaseOrderById(long id);
    void delete(PurchaseOrder purchaseOrder);
}
