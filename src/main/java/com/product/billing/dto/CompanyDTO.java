package com.product.billing.dto;

import java.util.List;

import com.product.billing.model.Company;
import com.product.billing.model.User;

public class CompanyDTO {

	private long id;
	private String companyName;
	private long phone;
	private String address;
	private String webSite;
	private int isDeleted;
	private List<User> users;
	
	public CompanyDTO() {
		
	}
	
	public CompanyDTO(Company company){
		this.id=company.getId();
		this.companyName=company.getCompanyName();
		this.phone=company.getPhone();
		this.address=company.getAddress();
		this.webSite=company.getWebSite();
		this.isDeleted=company.getIsDeleted();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
