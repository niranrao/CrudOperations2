package com.lumen.ebonding.dto;

import java.util.Date;

public class CustomerConfigurationDTO {

	private Integer id;
	private Integer version;
	private CustomerDTO customer;
	private String businessProcess;
	private String action;
	private String source;
	private String target;
	private boolean active;
	private String ciClass;
	private String ciType;
	private String customerUserId;
	private String customerPassword;
	private String modifedBy;
	private Date modifiedDate;
	private String ciTypeSubType;
	private String ticketSysCompanyName;
	private String srcToolDateFormat;
	private String srcTicketCreateDateField;
	private String srcTicketUpdateDateField;
	private String sbComponent;
	private String targetToolUserId;
	private String targetUserId;
	private String targetPassword;
	private String srcTicketIdField;
	private String srcExternalIdField;
	private String targetTicketIdField;
	private String targetExternalIdField;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public String getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCiClass() {
		return ciClass;
	}

	public void setCiClass(String ciClass) {
		this.ciClass = ciClass;
	}

	public String getCiType() {
		return ciType;
	}

	public void setCiType(String ciType) {
		this.ciType = ciType;
	}

	public String getCustomerUserId() {
		return customerUserId;
	}

	public void setCustomerUserId(String customerUserId) {
		this.customerUserId = customerUserId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getModifedBy() {
		return modifedBy;
	}

	public void setModifedBy(String modifedBy) {
		this.modifedBy = modifedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCiTypeSubType() {
		return ciTypeSubType;
	}

	public void setCiTypeSubType(String ciTypeSubType) {
		this.ciTypeSubType = ciTypeSubType;
	}

	public String getTicketSysCompanyName() {
		return ticketSysCompanyName;
	}

	public void setTicketSysCompanyName(String ticketSysCompanyName) {
		this.ticketSysCompanyName = ticketSysCompanyName;
	}

	public String getSrcToolDateFormat() {
		return srcToolDateFormat;
	}

	public void setSrcToolDateFormat(String srcToolDateFormat) {
		this.srcToolDateFormat = srcToolDateFormat;
	}

	public String getSrcTicketCreateDateField() {
		return srcTicketCreateDateField;
	}

	public void setSrcTicketCreateDateField(String srcTicketCreateDateField) {
		this.srcTicketCreateDateField = srcTicketCreateDateField;
	}

	public String getSrcTicketUpdateDateField() {
		return srcTicketUpdateDateField;
	}

	public void setSrcTicketUpdateDateField(String srcTicketUpdateDateField) {
		this.srcTicketUpdateDateField = srcTicketUpdateDateField;
	}

	public String getSbComponent() {
		return sbComponent;
	}

	public void setSbComponent(String sbComponent) {
		this.sbComponent = sbComponent;
	}

	public String getTargetToolUserId() {
		return targetToolUserId;
	}

	public void setTargetToolUserId(String targetToolUserId) {
		this.targetToolUserId = targetToolUserId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getTargetPassword() {
		return targetPassword;
	}

	public void setTargetPassword(String targetPassword) {
		this.targetPassword = targetPassword;
	}

	public String getSrcTicketIdField() {
		return srcTicketIdField;
	}

	public void setSrcTicketIdField(String srcTicketIdField) {
		this.srcTicketIdField = srcTicketIdField;
	}

	public String getSrcExternalIdField() {
		return srcExternalIdField;
	}

	public void setSrcExternalIdField(String srcExternalIdField) {
		this.srcExternalIdField = srcExternalIdField;
	}

	public String getTargetTicketIdField() {
		return targetTicketIdField;
	}

	public void setTargetTicketIdField(String targetTicketIdField) {
		this.targetTicketIdField = targetTicketIdField;
	}

	public String getTargetExternalIdField() {
		return targetExternalIdField;
	}

	public void setTargetExternalIdField(String targetExternalIdField) {
		this.targetExternalIdField = targetExternalIdField;
	}

}