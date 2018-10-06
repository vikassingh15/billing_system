package com.product.billing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.billing.dto.RoleDTO;
import com.product.billing.model.Role;
import com.product.billing.repository.RoleRepository;
import com.product.billing.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
   
    @Autowired
    private RoleRepository roleRepository;
    
	@Override
	public List<Role> getRoles() {
		return this.roleRepository.findAll();
	}
	@Override
	public void save(Role role) {
		this.roleRepository.save(role);
	}
	@Override
	public RoleDTO getRoleById(long id) {
		return new RoleDTO(this.roleRepository.getOne(id));
	}
	@Override
	public void delete(Role role) {
		this.roleRepository.delete(role);
	}

	@Override
	public Role getRoleByName(String name) {
		return this.roleRepository.findFirstByName(name);
	}


}
