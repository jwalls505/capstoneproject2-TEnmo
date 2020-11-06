package com.techelevator.tenmo.model;

public class Transfer {
	private int transferId;
	private int transferType;
	private int statusId;
	private int accountFrom;
	private int accountTo;
	private double amount;
	
	
	public Transfer() {
		
	}
	
	public Transfer(int transferType, int statusId, int accountFrom, int accountTo, double amount) {
	    this.statusId = statusId;
	    this.transferType = transferType;
	    this.accountFrom = accountFrom;
	    this.accountTo = accountTo;
	    this.amount = amount;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public int getTransferType() {
		return transferType;
	}

	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}

	public int getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
