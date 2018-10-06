package com.product.billing.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.product.billing.dto.CustomerDTO;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String website;
    private long companyId;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Address> addresses;

    private long mobile;
    private long telephone;
    private String careofPerson;
    private long copmobileNo;
    private int isDeleted;
    private String paymentTerms;

    public Customer() {
    }

    public Customer(CustomerDTO customerDTO) {
        this.id = customerDTO.getId();
        this.name = customerDTO.getName();
        this.email = customerDTO.getEmail();
        this.website = customerDTO.getWebsite();
        this.mobile = customerDTO.getMobile();
        this.telephone = customerDTO.getPhone();
        this.careofPerson = customerDTO.getCareofPerson();
        this.isDeleted=customerDTO.getIsDeleted();
        this.copmobileNo=customerDTO.getCopmobileNo();
        this.paymentTerms=customerDTO.getPaymentTerms();

        Address address = new Address();
        address.setCustomer(this);
        address.setAddress1(customerDTO.getAddress());
        address.setCity(customerDTO.getCity());
        address.setState(customerDTO.getState());
        address.setZip(String.valueOf(customerDTO.getPin()));
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

    public String getCareofPerson() {
        return careofPerson;
    }

    public void setCareofPerson(String careofPerson) {
        this.careofPerson = careofPerson;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
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
