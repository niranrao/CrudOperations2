package com.lumen.ebonding.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ebonding_master_audit",catalog = "legacy_ebonding")
public class EbondingHistory implements Serializable{

	/**
	 * Class to manage Ebonding History for Incident and Change tickets
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "mapping_id")
	private int id;
	
	@Column(name = "master_id")
	private int masterId;
	
	@Column(name = "remedy_ticket_id")
	private String remedyTicketId;
	
	@Column(name = "customer_ticket_id")
	private String customerTicketId;
	
	@Column(name = "request_from")
	private String requestFrom;
	
	@Column(name = "request_to")
	private String requestTo;
	
	@Column(name = "remedy_ticket_status")
	private String remedyTicketStatus;
	
	@Column(name="customer_ticket_status")
	private String customerTicketStatus;
	
	@Column(name="ebonding_result")
	private String ebondingResult;
	
	@Column(name ="transcation_result")
	private String transactionResult;
	
	@Column(name = "created_time")
	private Timestamp createdTime;
	

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

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
}