package com.product.billing.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.product.billing.model.Receipt;
import com.product.billing.model.ReceiptItems;
import com.product.billing.model.Vendor;

public class ReceiptDTO {

	private long id;
	private Vendor vendor;
	private String newClientName;
	private int paymodetype;
	private long receipNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date receiptDate;
	private long invoiceId;
	private int isDeleted;
    private List<ReceiptItemsDTO> receiptItemsDTOs;
    private double totalamount;
    private long companyId;
    
    public ReceiptDTO() {
    	
    }
    
    public ReceiptDTO(Receipt receipt) {
    	this.id=receipt.getId();
    	this.vendor=receipt.getVendor();
    	this.newClientName=receipt.getNewClientName();
    	this.paymodetype=receipt.getPaymodetype();
    	this.receipNo=receipt.getReceipNo();
    	this.receiptDate=receipt.getReceiptDate();
    	this.invoiceId=receipt.getInvoiceId();
    	this.isDeleted=receipt.getIsDeleted();
    	this.totalamount=receipt.getTotalamount();
    	this.companyId=receipt.getCompanyId();
    	
    	List<ReceiptItemsDTO> lstreceiptitems =new ArrayList<>();
    	
    	for(ReceiptItems receiptItems:receipt.getReceiptItems()) {
    		ReceiptItemsDTO receiptItemsDTO=new ReceiptItemsDTO();
    		receiptItemsDTO.setId(receiptItems.getId());
    		receiptItemsDTO.setInvoiceItemId(receiptItems.getInvoiceItemId());
    		receiptItemsDTO.setReceipt(receipt);
    		receiptItemsDTO.setReceiptAmount(receiptItems.getReceiptAmount());
    		receiptItemsDTO.setStatus(receiptItems.getStatus());
    		lstreceiptitems.add(receiptItemsDTO);
    	}
    	this.receiptItemsDTOs=lstreceiptitems;
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
	public List<ReceiptItemsDTO> getReceiptItemsDTOs() {
		return receiptItemsDTOs;
	}
	public void setReceiptItemsDTOs(List<ReceiptItemsDTO> receiptItemsDTOs) {
		this.receiptItemsDTOs = receiptItemsDTOs;
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
