package com.product.billing.dto;

import com.product.billing.model.Address;
import com.product.billing.model.Customer;

import java.util.Set;

public class CustomerDTO {

    private long id;
    private String name;
    private String address;
    private String city;
    private int pin;
    private String state;
    private String careofPerson;
    private String email;
    private String website;
    private long mobile;
    private long phone;
    private int isDeleted;
    private long copmobileNo;
    private String paymentTerms;
    private long companyId;


    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.careofPerson = customer.getCareofPerson();
        this.email = customer.getEmail();
        this.website = customer.getWebsite();
        this.mobile = customer.getMobile();
        this.phone = customer.getTelephone();
        this.isDeleted=customer.getIsDeleted();
        this.copmobileNo=customer.getCopmobileNo();
        this.paymentTerms=customer.getPaymentTerms();

        Set<Address> customerAddresses = customer.getAddresses();

        if(!customerAddresses.isEmpty()){
            customerAddresses.forEach(address1 -> {
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

    public String getCareofPerson() {
        return careofPerson;
    }

    public void setCareofPerson(String careofPerson) {
        this.careofPerson = careofPerson;
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

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public long getCopmobileNo() {
		return copmobileNo;
	}

	public void setCopmobileNo(long copmobileNo) {
		this.copmobileNo = copmobileNo;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
    
}
