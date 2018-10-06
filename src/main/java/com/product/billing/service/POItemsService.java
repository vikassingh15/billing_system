package com.product.billing.service;

import java.util.List;

import com.product.billing.dto.POItemsDTO;
import com.product.billing.model.POItems;

public interface POItemsService {
    List<POItems> gePOItems();
    void save(POItems poItems);
    POItemsDTO getPOItemsDTOById(long id);
}
