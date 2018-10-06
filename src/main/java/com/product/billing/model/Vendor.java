package com.product.billing.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.product.billing.dto.VendorDTO;

@Entity
@Table(name = "vendor")
public class Vendor {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String website;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private Set<Address> addresses;
    
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    private long mobile;
    private long telephone;
    private long fax;
    private String contactPerson;
    private int isDeleted;
    private long companyId;

	public Vendor() {
    }

    public Vendor(VendorDTO vendorDTO) {
        this.id = vendorDTO.getId();
        this.name = vendorDTO.getName();
        this.email = vendorDTO.getEmail();
        this.website = vendorDTO.getWebsite();
        this.mobile = vendorDTO.getMobile();
        this.telephone = vendorDTO.getPhone();
        this.fax = Long.parseLong(vendorDTO.getFax());
        this.contactPerson = vendorDTO.getContactPerson();
        this.isDeleted=vendorDTO.getIsDeleted();
        this.companyId=vendorDTO.getCompanyId();

        Address address = new Address();
        address.setVendor(this);
        address.setAddress1(vendorDTO.getAddress());
        address.setCity(vendorDTO.getCity());
        address.setState(vendorDTO.getState());
        address.setZip(String.valueOf(vendorDTO.getPin()));
        this.addresses = new HashSet<>();
        this.addresses.add(address);
    }
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Set<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public long getTelephone() {
		return telephone;
	}
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	public long getFax() {
		return fax;
	}
	public void setFax(long fax) {
		this.fax = fax;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public List<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
    public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
    
}
