package com.product.billing.service;

import com.product.billing.dto.CustomerDTO;
import com.product.billing.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();
    
    List<Customer> getCustomers(long companyId,int isDeleted);

    void save(Customer customer);

    CustomerDTO getCustomerById(long id);
}