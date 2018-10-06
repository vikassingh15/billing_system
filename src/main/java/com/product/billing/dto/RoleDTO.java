package com.product.billing.dto;

import java.util.Set;

import com.product.billing.model.Role;
import com.product.billing.model.User;

public class RoleDTO {
	 
	private Long id;
	private String name;
	private Set<User> users;
	
	public RoleDTO() {
		
	}
	public RoleDTO(Role role) {
		this.id=role.getId();
		this.name=role.getName();
		this.users=role.getUsers();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
