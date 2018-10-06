package com.product.billing.service;

import java.util.List;

import com.product.billing.dto.RoleDTO;
import com.product.billing.model.Role;

public interface RoleService {
	List<Role> getRoles();
	void save(Role role);
	RoleDTO getRoleById(long id);
	void delete(Role role);
    Role getRoleByName(String name);
}
