package com.product.billing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.billing.model.Address;
import com.product.billing.repository.AddressRepository;
import com.product.billing.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;


    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }
}
