package com.product.billing.service.impl;

import com.product.billing.dto.POItemsDTO;
import com.product.billing.model.POItems;
import com.product.billing.repository.POItemsRepository;
import com.product.billing.service.POItemsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POItemsServiceImpl implements POItemsService {

	private final POItemsRepository poItemsRepository;

    @Autowired
    public POItemsServiceImpl(POItemsRepository poItemsRepository) {
        this.poItemsRepository = poItemsRepository;
    }

	@Override
	public List<POItems> gePOItems() {
		return poItemsRepository.findAll();
	}

	@Override
	public void save(POItems poItems) {
		poItemsRepository.save(poItems);
	}

	@Override
	public POItemsDTO getPOItemsDTOById(long id) {
		return new POItemsDTO(this.poItemsRepository.getOne(id));
	}
}
