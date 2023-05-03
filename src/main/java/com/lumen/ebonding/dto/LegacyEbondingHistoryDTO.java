package com.lumen.ebonding.dto;

import java.util.Date;

public class LegacyEbondingHistoryDTO {

	private int id;
	private int masterId;
	private String remedyTicketId;
	private String customerTicketId;
	private String requestFrom;
	private String requestTo;
	private String remedyTicketStatus;
	private String customerTicketStatus;
	private String ebondingResult;
	private String transactionResult;
	private Date createdTime;
	
	public int getMasterId() {
		return masterId;
	}
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRemedyTicketId() {
		return remedyTicketId;
	}
	public void setRemedyTicketId(String remedyTicketId) {
		this.remedyTicketId = remedyTicketId;
	}
	public String getCustomerTicketId() {
		return customerTicketId;
	}
	public void setCustomerTicketId(String customerTicketId) {
		this.customerTicketId = customerTicketId;
	}
	public String getRequestFrom() {
		return requestFrom;
	}
	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}
	public String getRequestTo() {
		return requestTo;
	}
	public void setRequestTo(String requestTo) {
		this.requestTo = requestTo;
	}
	public String getRemedyTicketStatus() {
		return remedyTicketStatus;
	}
	public void setRemedyTicketStatus(String remedyTicketStatus) {
		this.remedyTicketStatus = remedyTicketStatus;
	}
	public String getCustomerTicketStatus() {
		return customerTicketStatus;
	}
	public void setCustomerTicketStatus(String customerTicketStatus) {
		this.customerTicketStatus = customerTicketStatus;
	}
	public String getEbondingResult() {
		return ebondingResult;
	}
	public void setEbondingResult(String ebondingResult) {
		this.ebondingResult = ebondingResult;
	}
	public String getTransactionResult() {
		return transactionResult;
	}
	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}	
}