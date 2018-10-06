package com.product.billing.dto;

import com.product.billing.model.POItems;
import com.product.billing.model.PurchaseOrder;

public class POItemsDTO {

	private long id;
	private PurchaseOrder purchaseOrder;
	private String type;
	private String particulars;
	private double sqFt;
	private double rate;
	private double amount;
	
	public POItemsDTO(POItems poItems) { 
	}
	
	public POItemsDTO() { 
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParticulars() {
		return particulars;
	}
	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}
	public double getSqFt() {
		return sqFt;
	}
	public void setSqFt(double sqFt) {
		this.sqFt = sqFt;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
