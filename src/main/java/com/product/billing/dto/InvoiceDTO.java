package com.product.billing.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.product.billing.model.Invoice;
import com.product.billing.model.InvoiceItem;
import com.product.billing.model.Vendor;


public class InvoiceDTO {

    private long id;
    private Vendor vendor;
    private String contactPerson;
    private String invoiceNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate; 
    private String address; 
    private double discount;
    private double advance;
    private double roundOff;
    private double payableTotal;
    private int isDeleted;
    private int discounttype;
    private double totalamount;
    private List<InvoiceItemDTO> invoiceItems;
    private long companyId;
    
    public InvoiceDTO() {
    	
    }
    
    public InvoiceDTO(Invoice invoice) {
    	this.id=invoice.getId();
    	this.vendor=invoice.getVendor();
    	this.address=invoice.getAddress();
    	this.invoiceNo=invoice.getInvoiceNo();
    	this.contactPerson=invoice.getContactPerson();
    	this.invoiceDate=invoice.getInvoiceDate();
    	this.discount=invoice.getDiscount();
    	this.advance=invoice.getAdvance();
    	this.roundOff=invoice.getRoundOff();
    	this.payableTotal=invoice.getPayableTotal();
    	this.isDeleted=invoice.getIsDeleted();
    	this.totalamount=invoice.getTotalamount();
    	this.discounttype=invoice.getDiscounttype();
    	this.companyId=invoice.getCompanyId();
    	
    	List<InvoiceItemDTO> lstInvoiceItemsDTO =new ArrayList<>();
    	
    	for(InvoiceItem invoiceItem:invoice.getInvoiceItems()) {
    		InvoiceItemDTO invoiceItemDTO=new InvoiceItemDTO();
    		invoiceItemDTO.setId(invoiceItem.getId());
    		invoiceItemDTO.setAmount(invoiceItem.getAmount());
    		invoiceItemDTO.setLength(invoiceItem.getLength());
    		invoiceItemDTO.setWidth(invoiceItem.getWidth());
    		invoiceItemDTO.setSide(invoiceItem.getSide());
    		invoiceItemDTO.setParticulars(invoiceItem.getParticulars()); 
    		invoiceItemDTO.setProductType(invoiceItem.getProductType());
    		invoiceItemDTO.setQuantity(invoiceItem.getQuantity()); 
    		invoiceItemDTO.setRate(invoiceItem.getRate());
    		invoiceItemDTO.setType(invoiceItem.getType());
    		lstInvoiceItemsDTO.add(invoiceItemDTO);
    	}
    	this.invoiceItems=lstInvoiceItemsDTO;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor =vendor;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    public double getRoundOff() {
        return roundOff;
    }

    public void setRoundOff(double roundOff) {
        this.roundOff = roundOff;
    }

    public double getPayableTotal() {
        return payableTotal;
    }

    public void setPayableTotal(double payableTotal) {
        this.payableTotal = payableTotal;
    }

    public List<InvoiceItemDTO> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemDTO> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public int getDiscounttype() {
		return discounttype;
	}

	public void setDiscounttype(int discounttype) {
		this.discounttype = discounttype;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
    public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
    
}
