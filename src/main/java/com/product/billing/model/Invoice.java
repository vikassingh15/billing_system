package com.product.billing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.product.billing.dto.InvoiceDTO;
import com.product.billing.dto.InvoiceItemDTO;

/**
 * @author com
 *
 */
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="vendor_id")
    private Vendor vendor;
    private String address;
    private String contactPerson;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
	private String invoiceNo;
    private double discount;
    private double advance;
    private double roundOff;
    private double payableTotal;
    private int isDeleted;
    private int discounttype;
    private double totalamount;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems;
    private long companyId;

    public Invoice(InvoiceDTO invoiceDTO) {
    	this.id=invoiceDTO.getId();
    	this.vendor=invoiceDTO.getVendor();
    	this.invoiceNo=invoiceDTO.getInvoiceNo();
    	this.invoiceDate=invoiceDTO.getInvoiceDate();
    	this.address=invoiceDTO.getAddress();
    	this.contactPerson=invoiceDTO.getContactPerson();
    	this.discount=invoiceDTO.getDiscount();
    	this.advance=invoiceDTO.getAdvance();
    	this.roundOff=invoiceDTO.getRoundOff();
    	this.payableTotal=invoiceDTO.getPayableTotal();
    	this.isDeleted=invoiceDTO.getIsDeleted();
    	this.totalamount=invoiceDTO.getTotalamount();
        this.discounttype=invoiceDTO.getDiscounttype();
        this.companyId=invoiceDTO.getCompanyId();

    	
    	List<InvoiceItem> lstInvoiceItems =new ArrayList<>();
    	
    	for(InvoiceItemDTO invoiceItemDTO:invoiceDTO.getInvoiceItems()) {
    		InvoiceItem invoiceItem=new InvoiceItem();
    		invoiceItem.setId(invoiceItemDTO.getId());
    		invoiceItem.setInvoice(this);
    		invoiceItem.setAmount(invoiceItemDTO.getAmount());
    		invoiceItem.setLength(invoiceItemDTO.getLength());
    		invoiceItem.setSide(invoiceItemDTO.getSide());
    		invoiceItem.setWidth(invoiceItemDTO.getWidth());
    		invoiceItem.setParticulars(invoiceItemDTO.getParticulars()); 
    		invoiceItem.setProductType(invoiceItemDTO.getProductType());
    		invoiceItem.setQuantity(invoiceItemDTO.getQuantity()); 
    		invoiceItem.setRate(invoiceItemDTO.getRate());
    		invoiceItem.setType(invoiceItemDTO.getType());
    		lstInvoiceItems.add(invoiceItem);
    	}
    	this.invoiceItems=lstInvoiceItems;
    }

    public Invoice() {

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
        this.vendor = vendor;
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

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
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
