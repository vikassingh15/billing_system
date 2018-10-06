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

import com.product.billing.dto.POItemsDTO;
import com.product.billing.dto.PurchaseOrderDTO;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(name="vendor_id")
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
    private double totalamount;
    private int isDeleted;
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<POItems> poitems;
    private long companyId;
    
    public PurchaseOrder()
    {
    	
    }
    
	public PurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {

		this.id=purchaseOrderDTO.getId();
        this.vendor = purchaseOrderDTO.getVendor();
        this.newCustomerName = purchaseOrderDTO.getNewCustomerName();
        this.contactPersonName=purchaseOrderDTO.getContactPersonName();
        this.address = purchaseOrderDTO.getAddress();
        this.poDate = purchaseOrderDTO.getPoDate();
        this.priceApprovedBy = purchaseOrderDTO.getPriceApprovedBy();
        this.discount = purchaseOrderDTO.getDiscount();
        this.advance = purchaseOrderDTO.getAdvance();
        this.roundOff = purchaseOrderDTO.getRoundOff();
        this.payableTotal = purchaseOrderDTO.getPayableTotal();
        this.isDeleted=purchaseOrderDTO.getIsDeleted();
        this.advance = purchaseOrderDTO.getAdvance();
        this.totalamount=purchaseOrderDTO.getTotalamount();
        this.discounttype=purchaseOrderDTO.getDiscounttype();
        this.companyId=purchaseOrderDTO.getCompanyId();
        
        List<POItems> lstPoItems =new ArrayList<>();
    	
    	for(POItemsDTO poItemsDTOs:purchaseOrderDTO.getPoitems()) {
    		POItems poItems=new POItems();
    		poItems.setId(poItemsDTOs.getId());
    		poItems.setPurchaseOrder(this);
    		poItems.setAmount(poItemsDTOs.getAmount());
    		poItems.setType(poItemsDTOs.getType());
    		poItems.setParticulars(poItemsDTOs.getParticulars());
    		poItems.setSqFt(poItemsDTOs.getSqFt());
    		poItems.setRate(poItemsDTOs.getRate());
    		lstPoItems.add(poItems);
    	}
    	this.poitems=lstPoItems;
    
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
	public List<POItems> getPoitems() {
		return poitems;
	}
	public void setPoitems(List<POItems> poitems) {
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
