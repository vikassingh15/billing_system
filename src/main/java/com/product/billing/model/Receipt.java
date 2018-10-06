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

import com.product.billing.dto.ReceiptDTO;
import com.product.billing.dto.ReceiptItemsDTO;

@Entity
@Table(name="receipt")
public class Receipt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
    @JoinColumn(name="customer_id")
	private Vendor vendor;
	private String newClientName;
	private int paymodetype;
	private long receipNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date receiptDate;
	private long invoiceId;
	private int isDeleted;
	@OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<ReceiptItems> receiptItems;
    private double totalamount;
    private long companyId;
	
	public Receipt() {
		
	}
	
	public Receipt(ReceiptDTO receiptDTO) {
    	this.id=receiptDTO.getId();
    	this.vendor=receiptDTO.getVendor();
    	this.newClientName=receiptDTO.getNewClientName();
    	this.paymodetype=receiptDTO.getPaymodetype();
    	this.receipNo=receiptDTO.getReceipNo();
    	this.receiptDate=receiptDTO.getReceiptDate();
    	this.invoiceId=receiptDTO.getInvoiceId();
    	this.isDeleted=receiptDTO.getIsDeleted();
    	this.totalamount=receiptDTO.getTotalamount();
    	this.companyId=receiptDTO.getCompanyId();
    	
    	List<ReceiptItems> lstreceiptitems =new ArrayList<>();
    	
    	for(ReceiptItemsDTO receiptItemsDTO:receiptDTO.getReceiptItemsDTOs()) {
    		ReceiptItems receiptItems=new ReceiptItems();
    		receiptItems.setId(receiptItemsDTO.getId());
    		receiptItems.setReceipt(this);
    		receiptItems.setInvoiceItemId(receiptItemsDTO.getInvoiceItemId());
    		receiptItems.setReceiptAmount(receiptItemsDTO.getReceiptAmount());
    		receiptItems.setStatus(receiptItemsDTO.getStatus());
    		lstreceiptitems.add(receiptItems);
    	}
    	this.receiptItems=lstreceiptitems;
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
	public String getNewClientName() {
		return newClientName;
	}
	public void setNewClientName(String newClientName) {
		this.newClientName = newClientName;
	}
	public int getPaymodetype() {
		return paymodetype;
	}
	public void setPaymodetype(int paymodetype) {
		this.paymodetype = paymodetype;
	}
	public long getReceipNo() {
		return receipNo;
	}
	public void setReceipNo(long receipNo) {
		this.receipNo = receipNo;
	}
	public Date getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	public long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public List<ReceiptItems> getReceiptItems() {
		return receiptItems;
	}
	public void setReceiptItems(List<ReceiptItems> receiptItems) {
		this.receiptItems = receiptItems;
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
    public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
}
