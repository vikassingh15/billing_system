package com.product.billing.dto;

import com.product.billing.model.Receipt;

public class ReceiptItemsDTO {

	private long id;
	private Receipt receipt;
	private long invoiceItemId;
	private int status;
	private double receiptAmount;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	public long getInvoiceItemId() {
		return invoiceItemId;
	}
	public void setInvoiceItemId(long invoiceItemId) {
		this.invoiceItemId = invoiceItemId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	
}
