package com.product.billing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.billing.dto.CustomerDTO;
import com.product.billing.model.Customer;
import com.product.billing.repository.CustomerRepository;
import com.product.billing.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public CustomerDTO getCustomerById(long id) {
        return new CustomerDTO(this.customerRepository.getOne(id));
    }

	@Override
	public List<Customer> getCustomers(long companyId,int isDeleted) {
		return this.customerRepository.findAllByCompanyIdAndIsDeleted(companyId,isDeleted);
	}
}
