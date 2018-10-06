package com.product.billing.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.product.billing.model.POItems;
import com.product.billing.model.PurchaseOrder;
import com.product.billing.model.Vendor;

public class PurchaseOrderDTO {
	
	private long id;
	private Vendor vendor;
	private String newCustomerName;
	private String contactPersonName;
	private String address;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date poDate;
	private String priceApprovedBy;
    private double discount;
    private int discounttype;
    private double advance;
    private double roundOff;
    private double payableTotal;
    private int isDeleted;
    private double totalamount;
    private List<POItemsDTO> poitems;
    private long companyId;
    
    public PurchaseOrderDTO() {
	}

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.getId();
        this.vendor = purchaseOrder.getVendor();
        this.newCustomerName = purchaseOrder.getNewCustomerName();
        this.contactPersonName=purchaseOrder.getContactPersonName();
        this.address = purchaseOrder.getAddress();
        this.poDate = purchaseOrder.getPoDate();
        this.priceApprovedBy = purchaseOrder.getPriceApprovedBy();
        this.discount = purchaseOrder.getDiscount();
        this.advance = purchaseOrder.getAdvance();
        this.roundOff = purchaseOrder.getRoundOff();
        this.payableTotal = purchaseOrder.getPayableTotal();
        this.advance = purchaseOrder.getAdvance();
        this.isDeleted = purchaseOrder.getIsDeleted();
        this.totalamount=purchaseOrder.getTotalamount();
        this.discounttype=purchaseOrder.getDiscounttype();
        this.companyId=purchaseOrder.getCompanyId();
        
        List<POItemsDTO> lstPoItemsDTO =new ArrayList<>();
    	
    	for(POItems poItems:purchaseOrder.getPoitems()) {
    		POItemsDTO poItemsDTO=new POItemsDTO();
    		poItemsDTO.setId(poItems.getId());
    		poItemsDTO.setAmount(poItems.getAmount());
    		poItemsDTO.setType(poItems.getType());
    		poItemsDTO.setParticulars(poItems.getParticulars());
    		poItemsDTO.setSqFt(poItems.getSqFt());
    		poItemsDTO.setRate(poItems.getRate());
    		lstPoItemsDTO.add(poItemsDTO);
    	}
    	this.poitems=lstPoItemsDTO;
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
	public String getNewCustomerName() {
		return newCustomerName;
	}
	public void setNewCustomerName(String newCustomerName) {
		this.newCustomerName = newCustomerName;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public String getPriceApprovedBy() {
		return priceApprovedBy;
	}
	public void setPriceApprovedBy(String priceApprovedBy) {
		this.priceApprovedBy = priceApprovedBy;
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
	public List<POItemsDTO> getPoitems() {
		return poitems;
	}
	public void setPoitems(List<POItemsDTO> poitems) {
		this.poitems = poitems;
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
    public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
}
