package com.lumen.ebonding.dto;

import java.util.Date;

/**
 * DTO for Customer Details
 * 
 * @author Devesh Joshi
 * @since Oct 12, 2020
 */
public class CustomerDTO {

	private Integer id;
	private String customerName;
	private Date ebondingStartDate;
	private Date ebondingEndDate;
	private String timeZone;
	private String custTicketSystem;
	private String custSpocName;
	private String custSpocEmail;
	private String custSpocContact;
	private String custEmail;
	private String lumenOpsSpocName;
	private String lumenOpsSpocEmail;
	private String lumenEscalationSpocName;
	private String lumenEscalationSpocEmailid;
	private Date custOnboardDate;
	private boolean active;
	private boolean ebondIncident;
	private boolean ebondChangeRequest;
	private boolean ebondServiceRequest;
	private boolean ebondProblem;
	private boolean ebondCmdb;
	private String cmdbEbondDirection;
	private String comment;
	private String modifiedBy;
	private Date modifiedDate;
	private Date ebondIncidentStartDate;
	private Date ebondProblemStartDate;
	private Date ebondChangeStartDate;
	private Date ebondServiceStartDate;
	private Date ebondCmdbStartDate;
	private boolean scheduled;
	private boolean responseRequired;
	private int supportAPIDetailsId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getEbondingStartDate() {
		return ebondingStartDate;
	}

	public void setEbondingStartDate(Date ebondingStartDate) {
		this.ebondingStartDate = ebondingStartDate;
	}

	public Date getEbondingEndDate() {
		return ebondingEndDate;
	}

	public void setEbondingEndDate(Date ebondingEndDate) {
		this.ebondingEndDate = ebondingEndDate;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getCustTicketSystem() {
		return custTicketSystem;
	}

	public void setCustTicketSystem(String custTicketSystem) {
		this.custTicketSystem = custTicketSystem;
	}

	public String getCustSpocName() {
		return custSpocName;
	}

	public void setCustSpocName(String custSpocName) {
		this.custSpocName = custSpocName;
	}

	public String getCustSpocEmail() {
		return custSpocEmail;
	}

	public void setCustSpocEmail(String custSpocEmail) {
		this.custSpocEmail = custSpocEmail;
	}

	public String getCustSpocContact() {
		return custSpocContact;
	}

	public void setCustSpocContact(String custSpocContact) {
		this.custSpocContact = custSpocContact;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getLumenOpsSpocName() {
		return lumenOpsSpocName;
	}

	public void setLumenOpsSpocName(String lumenOpsSpocName) {
		this.lumenOpsSpocName = lumenOpsSpocName;
	}

	public String getLumenOpsSpocEmail() {
		return lumenOpsSpocEmail;
	}

	public void setLumenOpsSpocEmail(String lumenOpsSpocEmail) {
		this.lumenOpsSpocEmail = lumenOpsSpocEmail;
	}

	public String getLumenEscalationSpocName() {
		return lumenEscalationSpocName;
	}

	public void setLumenEscalationSpocName(String lumenEscalationSpocName) {
		this.lumenEscalationSpocName = lumenEscalationSpocName;
	}

	public String getLumenEscalationSpocEmailid() {
		return lumenEscalationSpocEmailid;
	}

	public void setLumenEscalationSpocEmailid(String lumenEscalationSpocEmailid) {
		this.lumenEscalationSpocEmailid = lumenEscalationSpocEmailid;
	}

	public Date getCustOnboardDate() {
		return custOnboardDate;
	}

	public void setCustOnboardDate(Date custOnboardDate) {
		this.custOnboardDate = custOnboardDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isEbondIncident() {
		return ebondIncident;
	}

	public void setEbondIncident(boolean ebondIncident) {
		this.ebondIncident = ebondIncident;
	}

	public boolean isEbondChangeRequest() {
		return ebondChangeRequest;
	}

	public void setEbondChangeRequest(boolean ebondChangeRequest) {
		this.ebondChangeRequest = ebondChangeRequest;
	}

	public boolean isEbondServiceRequest() {
		return ebondServiceRequest;
	}

	public void setEbondServiceRequest(boolean ebondServiceRequest) {
		this.ebondServiceRequest = ebondServiceRequest;
	}

	public boolean isEbondProblem() {
		return ebondProblem;
	}

	public void setEbondProblem(boolean ebondProblem) {
		this.ebondProblem = ebondProblem;
	}

	public boolean isEbondCmdb() {
		return ebondCmdb;
	}

	public void setEbondCmdb(boolean ebondCmdb) {
		this.ebondCmdb = ebondCmdb;
	}

	public String getCmdbEbondDirection() {
		return cmdbEbondDirection;
	}

	public void setCmdbEbondDirection(String cmdbEbondDirection) {
		this.cmdbEbondDirection = cmdbEbondDirection;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getEbondIncidentStartDate() {
		return ebondIncidentStartDate;
	}

	public void setEbondIncidentStartDate(Date ebondIncidentStartDate) {
		this.ebondIncidentStartDate = ebondIncidentStartDate;
	}

	public Date getEbondProblemStartDate() {
		return ebondProblemStartDate;
	}

	public void setEbondProblemStartDate(Date ebondProblemStartDate) {
		this.ebondProblemStartDate = ebondProblemStartDate;
	}

	public Date getEbondChangeStartDate() {
		return ebondChangeStartDate;
	}

	public void setEbondChangeStartDate(Date ebondChangeStartDate) {
		this.ebondChangeStartDate = ebondChangeStartDate;
	}

	public Date getEbondServiceStartDate() {
		return ebondServiceStartDate;
	}

	public void setEbondServiceStartDate(Date ebondServiceStartDate) {
		this.ebondServiceStartDate = ebondServiceStartDate;
	}

	public Date getEbondCmdbStartDate() {
		return ebondCmdbStartDate;
	}

	public void setEbondCmdbStartDate(Date ebondCmdbStartDate) {
		this.ebondCmdbStartDate = ebondCmdbStartDate;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public boolean isResponseRequired() {
		return responseRequired;
	}

	public void setResponseRequired(boolean responseRequired) {
		this.responseRequired = responseRequired;
	}

	public int getSupportAPIDetailsId() {
		return supportAPIDetailsId;
	}

	public void setSupportAPIDetailsId(int supportAPIDetailsId) {
		this.supportAPIDetailsId = supportAPIDetailsId;
	}

}
