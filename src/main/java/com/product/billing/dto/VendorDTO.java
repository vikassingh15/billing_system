package com.product.billing.dto;

import java.util.Set;

import com.product.billing.model.Address;
import com.product.billing.model.Vendor;

public class VendorDTO {

    private long id;
    private String name;
    private String address;
    private String city;
    private int pin;
    private String state;
    private String contactPerson;
    private String email;
    private String website;
    private long mobile;
    private long phone;
    private String fax;
    private int isDeleted;
    private long companyId;
    
    public VendorDTO() {
    }

    public VendorDTO(Vendor vendor) {
        this.id = vendor.getId();
        this.name = vendor.getName();
        this.contactPerson = vendor.getContactPerson();
        this.email = vendor.getEmail();
        this.website = vendor.getWebsite();
        this.mobile = vendor.getMobile();
        this.phone = vendor.getTelephone();
        this.fax = String.valueOf(vendor.getFax());
        this.isDeleted=vendor.getIsDeleted();
        this.companyId=vendor.getCompanyId();

        Set<Address> vendorAddresses = vendor.getAddresses();

        if(!vendorAddresses.isEmpty()){
        	vendorAddresses.forEach(address1 -> {
                this.address = address1.getAddress1();
                this.city = address1.getCity();
                this.pin = Integer.parseInt(address1.getZip());
                this.state = address1.getState();
            });
        }
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
