package com.product.billing.dto;

import java.util.Set;

import com.product.billing.model.Company;
import com.product.billing.model.Role;
import com.product.billing.model.User;

public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private String passwordConfirm;
	private Company company;
	private int isDeleted;
    private Set<Role> roles;
	
	public UserDTO() {
	}
	public UserDTO(User user) {
		this.id=user.getId();
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.passwordConfirm=user.getPasswordConfirm();
		this.company=user.getCompany();
		this.roles=user.getRoles();
		this.isDeleted=user.getIsDeleted();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}

