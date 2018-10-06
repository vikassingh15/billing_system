package com.product.billing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.product.billing.dto.CompanyDTO;

@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String companyName;
	private long phone;
	private String address;
	private String webSite;
	private int isDeleted;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users;

	
	public Company() {
	}
	
	public Company(CompanyDTO companyDTO) {
		this.id=companyDTO.getId();
		this.companyName=companyDTO.getCompanyName();
		this.phone=companyDTO.getPhone();
		this.address=companyDTO.getAddress();
		this.webSite=companyDTO.getWebSite();
		this.isDeleted=companyDTO.getIsDeleted();
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
